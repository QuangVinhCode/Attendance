import axios from "axios";
import { API_ATTENDANCE } from "./constant";

export default class AttendanceService {
  getAttendanceInfoByStudentAndClass = async (id_Class,id_Student) => {
    console.log("AttendanceService");
    return await axios.get(API_ATTENDANCE + "/attendance-info/" + id_Class + "/" + id_Student);
  };
}
