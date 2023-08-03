import React from "react";

import PdfPreview from "./pdf/PdfPreview";
import DocxPreview from "./word/DocxPreview";

interface FilePreviewProps {
  file: string;
  children?: React.ReactNode;
}

const FilePreview: React.FC<FilePreviewProps> = ({ file }) => {
  const suffix = file.substring(file.lastIndexOf(".") + 1).toLowerCase();

  switch (suffix) {
    case "pdf":
      return <PdfPreview file={file} />;
    case "docx" || "doc":
      return <DocxPreview file={file} />;
    default:
      return <></>;
  }
};

export default FilePreview;
