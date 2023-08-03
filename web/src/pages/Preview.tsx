import React from "react";
import { useLocation } from "react-router-dom";

import FilePreview from "../components/Dashboard/Preview/FilePreview";

const Preview: React.FC = () => {
  let location = useLocation();

  let search = location.search;
  let url = search.substring(search.lastIndexOf("=") + 1);

  return <FilePreview file={url} />;
};

export default Preview;
