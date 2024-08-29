import StudentService from "../services/studentService";

import {
  COMMON_ERROR_SET,
  COMMON_LOADING_SET,
  STUDENT_SET,
} from "./actionTypes";

export const getStudentInfo = (id) => async (dispatch) => {
  const service = new StudentService();

  try {
    console.log("Thông tin sinh viên");
    dispatch({
      type: COMMON_LOADING_SET,
      payload: true,
    });
    const response = await service.getStudentInfo(id);
    console.log(response);
    if (response.status === 200) {
      dispatch({
        type: STUDENT_SET,
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
