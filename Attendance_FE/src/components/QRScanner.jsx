import React, { useState, useEffect } from "react";
import { QrReader } from "react-qr-reader";
import { URL_HTTP } from "../services/auth.service";
import { useParams } from "react-router-dom";
import Header from "../components/Header";

import axios from "axios";
const QRScanner = () => {
  const params = useParams();
  const storedClassSession = sessionStorage.getItem("classdata");
  const ClassSession = storedClassSession
    ? JSON.parse(storedClassSession)
    : null;
  const [responseMessage, setResponseMessage] = useState("");
  useEffect(() => {
    if (responseMessage) {
      alert(responseMessage);
      setResponseMessage("");
    }
  }, [responseMessage]);

  const postDataToService = async (data) => {
    const attendance = {
      student_id: data,
      attendance_id: params.id,
      class_id: ClassSession.classId,
      status: true,
    };
    try {
      const response = await axios.post(
        URL_HTTP + "/attendances_students",
        attendance
      );
      console.log(response.data);
      setResponseMessage(response.message);
    } catch (error) {
      console.error(
        "Error occurred while posting data:" + error.response.data.message
      );
      setResponseMessage(error.response.data.message);
    }
  };

  return (
    <>
      <Header />
      
      <div
        style={{
          width: 500,
          height: 500,
          marginLeft: 400,
          justifyContent: "center",
        }}
        className=""
      >
        <QrReader
          onResult={(result, error) => {
            if (!!result) {
              postDataToService(result?.text);
            }

            if (!!error) {
              // console.info(error);
            }
          }}
          resolution={1200}
        />
        <p>{responseMessage}</p>
      </div>
    </>
  );
};

export default QRScanner;
