import {
  CATEGORIES_DELETE,
  CATEGORIES_SET,
  CATEGORIES_STATE_CLEAR,
  CATEGORY_SET,
} from "./../actions/actionTypes";

const initialState = {
  category: {},
  categories: [],
};

const categoryReducer = (state = initialState, { type, payload }) => {
  switch (type) {
    case CATEGORY_SET:
      return { ...state, category: payload };

    case CATEGORIES_SET:
      return { ...state, categories: payload };

    case CATEGORIES_DELETE:
      return {
        ...state,
        categories: state.categories.filter((item) => item.id !== payload),
      };

    case CATEGORIES_STATE_CLEAR:
      return {
        category: {},
        categories: [],
      };
    default:
      return state;
  }
};

export default categoryReducer;
