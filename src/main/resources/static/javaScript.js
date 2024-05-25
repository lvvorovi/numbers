// 'use strict'

const inputNumberElement = document.getElementById("inputNumber");
const resultElement = document.getElementById("result");
resultElement.textContent = "";

const fetchFunc = function (event) {
  event.preventDefault;

  if (inputNumberElement.value == "") {
    resultElement.textContent = "no value provided";
    return;
  }

  let status;

  fetch("/api/v1/codes/" + inputNumberElement.value)
    .then(function (response) {
      status = response.status;
      if (response.status == 404) {
        return;
      } else {
        return response.json();
      }
    })
    .then(function (data) {
      if (status == 200) {
        resultElement.textContent = data.countryName;
      } else if (status == 404) {
        resultElement.textContent =
          "Unable to determine country by provided number";
      } else if (status == 400 || status == 500) {
        resultElement.textContent = data.message;
      }
    });
};

document.getElementById("check").addEventListener("click", fetchFunc);

$(document).keydown(function (event) {
  if (event.which == "13") {
    event.preventDefault();
    fetchFunc(event);
  }
});
