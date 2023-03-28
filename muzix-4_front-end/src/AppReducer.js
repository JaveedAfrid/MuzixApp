const initialState = [['data1'],['data2']];

const reducer = (state, action) => {
    switch (action.type) {
        case 'ADD': {
            if (Array.isArray(action.value)) {
                console.log("add action triggered");
                return [...action.value]
            } else {
                return [...state]
            }
        }
    }
}
export { initialState };
export default reducer;