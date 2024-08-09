import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './PdfUpload.css'; // Importar o arquivo CSS

const PdfUpload: React.FC = () => {
    const [files, setFiles] = useState<File[]>([]);
    const [email, setEmail] = useState<string>('');
    const [fileName, setFileName] = useState<string>('merged'); // Nome padrão do arquivo
    const [mergedPdf, setMergedPdf] = useState<string | null>(null);
    const [history, setHistory] = useState<any[]>([]);
    const [loading, setLoading] = useState<boolean>(false);

    const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        if (e.target.files) {
            setFiles(Array.from(e.target.files));
        }
    };

    const handleEmailChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setEmail(e.target.value);
    };

    const handleFileNameChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setFileName(e.target.value);
    };

    const handleUpload = async () => {
        if (!email) {
            alert('Email is required.');
            return;
        }

        setLoading(true);
        const formData = new FormData();
        files.forEach(file => formData.append('files', file));
        formData.append('email', email);
        formData.append('fileName', fileName);

        try {
            const response = await axios.post('http://localhost:8080/api/merge-pdfs', formData, {
                responseType: 'blob',
            });
            const url = window.URL.createObjectURL(new Blob([response.data]));
            setMergedPdf(url);
            await fetchHistory();
        } catch (error) {
            console.error('Error merging PDFs:', error);
        } finally {
            setLoading(false);
        }
    };

    const fetchHistory = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/merge-history');
            setHistory(response.data);
        } catch (error) {
            console.error('Error fetching merge history:', error);
        }
    };

    useEffect(() => {
        fetchHistory();
    }, []);

    return (
        <div className="pdf-upload-container">
            <div className="upload-section">
                <input
                    type="file"
                    multiple
                    onChange={handleFileChange}
                    className="file-input"
                />
                <input
                    type="email"
                    value={email}
                    onChange={handleEmailChange}
                    placeholder="Enter your email"
                    className="email-input"
                />
                <input
                    type="text"
                    value={fileName}
                    onChange={handleFileNameChange}
                    placeholder="Enter output file name"
                    className="file-name-input"
                />
                <button
                    onClick={handleUpload}
                    className="upload-button"
                    disabled={loading}
                >
                    {loading ? 'Merging...' : 'Merge PDFs'}
                </button>
                {mergedPdf && (
                    <div className="download-section">
                        <a href={mergedPdf} download={`${fileName}.pdf`} className="download-link">
                            Download Merged PDF
                        </a>
                    </div>
                )}
            </div>
            <div className="history-section">
                <h2>Merge History</h2>
                <ul>
                    {history.map((entry, index) => (
                        <li key={index}>
                            {entry.fileName} - {entry.email} - {new Date(entry.createdDate).toLocaleString()}
                        </li>
                    ))}
                </ul>
            </div>
        </div>
    );
};

export default PdfUpload;
