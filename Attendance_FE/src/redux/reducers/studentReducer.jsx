import {
  STUDENT_SET
} from "../actions/actionTypes";
const initialState = {
  student: {},
  students: [],
};

const studentReducer = (state = initialState, { type, payload }) => {
  switch (type) {
    case STUDENT_SET:
      return { ...state, student: payload };

    default:
      return state;
  }
};

export default studentReducer;
