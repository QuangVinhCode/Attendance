import { ATTENDANCES_SET, ATTENDANCE_DELETE, ATTENDANCE_SET, ATTENDANCE_STATE_CLEAR } from "../actions/actionTypes";
const initialState = {
  attendance: {},
  attendances: [],
};

const attendanceReducer = (state = initialState, { type, payload }) => {
  switch (type) {
    case ATTENDANCE_SET:
      return { ...state, attendance: payload };
    case ATTENDANCES_SET:
      return { ...state, attendances: payload };
    case ATTENDANCE_DELETE:
      return { ...state, attendances: state.attendances.filter((item) => item.id !== payload), };
    case ATTENDANCE_STATE_CLEAR:
      return {
        attendance: {},
        attendances: [],
      };
    default:
      return state;
  }
};

export default attendanceReducer;
