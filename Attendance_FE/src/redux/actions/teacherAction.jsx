import TeacherService from "../services/teacherService";
import {
  TEACHERS_SET,
  COMMON_ERROR_SET,
  COMMON_LOADING_SET,
} from "./actionTypes";

export const getTeachers = () => async (dispatch) => {
  const service = new TeacherService();

  try {
    console.log("Danh sách giáo viên");
    dispatch({
      type: COMMON_LOADING_SET,
      payload: true,
    });
    const response = await service.getTeachers();
    console.log(response);
    if (response.status === 200) {
      dispatch({
        type: TEACHERS_SET,
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

