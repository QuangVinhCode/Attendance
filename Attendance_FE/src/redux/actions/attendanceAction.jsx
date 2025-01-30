import AttendanceService from "../services/attendanceService";
import {
  ATTENDANCE_STATE_CLEAR,
  ATTENDANCES_SET,
  COMMON_ERROR_SET,
  COMMON_LOADING_SET,
} from "./actionTypes";

export const getAttendanceInfoByStudentAndClass =
  (id_Class,id_Student) => async (dispatch) => {
    const service = new AttendanceService();

    try {
      console.log("Danh sách điểm danh");
      dispatch({
        type: COMMON_LOADING_SET,
        payload: true,
      });
      console.log(id_Class);
      console.log(id_Student);
      const response = await service.getAttendanceInfoByStudentAndClass(id_Class,id_Student);
      console.log(response);
      if (response.status === 200) {
        dispatch({
          type: ATTENDANCES_SET,
          payload: response.data,
        });
      } else {
        dispatch({
          type: COMMON_ERROR_SET,
          payload: response.message,
        });
      }
    } catch (error) {
      dispatch({
        type: COMMON_ERROR_SET,
        payload: error.response.data
          ? error.response.data.message
          : error.message,
      });
    }
    dispatch({
      type: COMMON_LOADING_SET,
      payload: false,
    });
  };

export const clearAttendance = () => (dispatch) => {
  dispatch({
    type: ATTENDANCE_STATE_CLEAR,
  });
};
