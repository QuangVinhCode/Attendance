import React, { useState } from "react";
import { URL_HTTP } from "../services/auth.service";

import axios from "axios";
const ExcelFilePicker = ({ id }) => {
  const [downloading, setDownloading] = useState(false);

  const handleDownload = async () => {
    if (downloading) {
      console.log("Downloading already in progress...");
      return;
    }

    try {
      setDownloading(true);

      const response = await axios({
        method: "POST",
        url: `${URL_HTTP}/classes/excel/${id}`,
        responseType: "blob",
      });

      const blob = new Blob([response.data], {
        type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
      });
      const url = window.URL.createObjectURL(blob);

      const link = document.createElement("a");
      link.href = url;
      link.setAttribute("download", "file.xlsx");

      document.body.appendChild(link);
      link.click();

      window.URL.revokeObjectURL(url);
      document.body.removeChild(link);

      setDownloading(false);
    } catch (error) {
      console.error("Error downloading file:", error);
      setDownloading(false);
    }
  };

  return (
    <>
      <div style={{backgroundColor:"#6b7280"}}>
        <div style={{ textAlign: "center", fontSize: 30}}>
          <button onClick={handleDownload} disabled={downloading}>
            Tải file excel thống kê
          </button>
        </div>
      </div>
    </>
  );
};
export default ExcelFilePicker;
