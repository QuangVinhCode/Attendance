import React, { useEffect, useState } from "react";
import { URL_HTTP } from "../services/auth.service";
import CardAttendance from "../components/CardAttendance";
import Header from "../components/Header";
import ExcelFilePicker from "../components/ExcelFilePicker";
import axios from "axios";

import { useParams, useNavigate } from "react-router-dom";
const HomeClass = () => {
  const [attendances, setAttendance] = useState([]);
  const params = useParams();
  const navigate = useNavigate();
  
  const getAttendance = async () => {
    try {
      const url = URL_HTTP + `/attendances/class/` + params.id;
      sessionStorage.setItem("classdata", JSON.stringify({classId: params.id}));
      const { data } = await axios.patch(url);
      setAttendance(data);
    } catch (error) {}
  };

  useEffect(() => {
    getAttendance();
  }, []);
  const onPerFom = () => {
    navigate("/create-attendance/" + params.id);
  };
  return (
    <>
      <Header />
      <ExcelFilePicker id={params.id}/>
      <div className="flex flex-col  bg-gray-500 items-center min-h-screen justify-center">
        <div className="absolute  opacity-80  z-0"></div>
          <button style={{backgroundColor:"lavender",fontSize:40,borderRadius:10}} onClick={() => onPerFom()}>Tạo buổi điểm danh</button>
        {attendances.map((item) => (
          <CardAttendance id={item.id} name={item.name} date={item.date}/>
        ))}
      </div>
    </>
  );
};

export default HomeClass;
