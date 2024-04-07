import {
 TEACHERS_SET
} from "../actions/actionTypes";
const initialState = {
  object: {},
  objects: [],
};

const teacherReducer = (state = initialState, { type, payload }) => {
  switch (type) {
    case TEACHERS_SET:
      return { ...state, objects: payload };

    default:
      return state;
  }
};

export default teacherReducer;
