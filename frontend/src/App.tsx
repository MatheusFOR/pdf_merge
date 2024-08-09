import React from 'react';
import './App.css';
import PdfUpload from './PdfUpload';

const App: React.FC = () => {
    return (
        <div className="app-container">
            <h1>PDF Merge Application</h1>
            <PdfUpload />
        </div>
    );
};

export default App;
