// this function validates a string to be a numeric value
function validateNumeric(string) {

    let nonNumericSymbols = new RegExp('[^0-9\.\-]');
    let minusNotvalid = new RegExp('[\-]$');
    // let minusWithZero = new RegExp('\-0');
    let dotInBegining = new RegExp('^[\.].*');
    let dotInTheEnd = new RegExp('.*[\.]$');


    // check if the string contains non-numeric symbols
    if (string.search(nonNumericSymbols) !== -1) {
        console.log('String contains non-numeric characters');
        return false;
    }

    if (string.search(minusNotvalid) !== -1) {
        console.log('String contains invalid minus');
        return false;
    }

    // if (string.search(minusWithZero) !== -1) {
    //     console.log('String contains minus with zero: that is not correct');
    //     return false;
    // }

    if (string.search(dotInBegining) !== -1 ) {
        console.log('String contains dot in the begining: that is not correct');
        return false;
    }

    if (string.search(dotInTheEnd) !== -1 ) {
        console.log('String cotains dot in the end: that os not correct');
        return false;
    }

    return true;
}

// this method parses appropriate float number from provided string
function parseAppropriateFloat(string) {

    if (validateNumeric(string)) {
        return parseFloat(string);
    }

    return undefined;
}

// this method validates form data with specific structure: {x: ..., y: ..., r: ...}
function validateFormData(data) {

    // validate x
    if (data.x === undefined || ![-3, -2, -1, 0, 1, 2, 3, 4, 5].includes(data.x)) {
        return {result: false, message: 'X is not appropriate'};
    }

    // validate y
    if (data.y === undefined || (data.y <= -5 || data.y >= 3)) {
        return {result: false, message: 'Y is not appropriate'};
    }

    // validate r
    if (data.r === undefined || ![1, 1.5, 2, 2.5, 3].includes(data.r)) {
        return {result: false, message: 'R is not appropriate'};
    }

    return {result: true};
}

// applies an error message to error block
function applyErrorMessage(message) {
    $('#error-message').text(message);
}

// clears error block
function clearErrors() {
    $('#error-message').text('');
}

export { validateNumeric, parseAppropriateFloat, validateFormData, applyErrorMessage, clearErrors };