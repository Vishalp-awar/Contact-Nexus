// // console.log("script loaded");
// //
// // let currentTheme=getTheme()
// // changeTheme(currentTheme);
// // function changeTheme(){
// //     // document.querySelector("html").addList.add(currentTheme);
// //
// //     const changethemeButton =document.querySelector('#theme_change_button')
// //     changethemeButton.addEventListener('click',function(){
// //         if(currentTheme=== "Light"){
// //             currentTheme = "Dark"
// //
// //         }else
// //         {
// //             currentTheme = "Light"
// //         }
// //
// //     })
// // }
// //
// // function setTheme(theme) {
// //     localStorage.setItem("theme", theme);
// // }
// //
// // function getTheme() {
// //     const theme= localStorage.getItem("theme");
// //     return theme ? theme : "Light";
// //
// // }
//
// console.log("script loaded");
//
// let currentTheme = getTheme();
// applyTheme(currentTheme);
//
// function applyTheme(theme) {
//     // Add the theme class to the HTML element
//     document.documentElement.classList.toggle('Dark', theme === "Dark");
// }
//
// function changeTheme() {
//     const changethemeButton = document.querySelector('#theme_change_button');
//
//     changethemeButton.addEventListener('click', function () {
//         // Toggle the theme between Light and Dark
//         if (currentTheme === "Light") {
//             currentTheme = "Dark";
//         } else {
//             currentTheme = "Light";
//         }
//
//         // Apply the new theme and save it to local storage
//         applyTheme(currentTheme);
//         setTheme(currentTheme);
//         changethemeButton.querySelector('span').textContent = currentTheme;
//     });
//
//
// }
//
// function setTheme(theme) {
//     localStorage.setItem("theme", theme);
// }
//
// function getTheme() {
//     const theme = localStorage.getItem("theme");
//     return theme ? theme : "Light";
// }
//
// changeTheme();
console.log("Script loaded");

// change theme work
let currentTheme = getTheme();
//initial -->

document.addEventListener("DOMContentLoaded", () => {
    changeTheme();
});


function changeTheme() {
    //set to web page

    changePageTheme(currentTheme, "");
    //set the listener to change theme button
    const changeThemeButton = document.querySelector("#theme_change_button");

    changeThemeButton.addEventListener("click", (event) => {
        let oldTheme = currentTheme;
        console.log("change theme button clicked");
        if (currentTheme === "dark") {
            //theme ko light
            currentTheme = "light";
        } else {
            //theme ko dark
            currentTheme = "dark";
        }
        console.log(currentTheme);
        changePageTheme(currentTheme, oldTheme);
    });
}

//set theme to localstorage
function setTheme(theme) {
    localStorage.setItem("theme", theme);
}

//get theme from localstorage
function getTheme() {
    let theme = localStorage.getItem("theme");
    return theme ? theme : "light";
}

//change current page theme
function changePageTheme(theme, oldTheme) {
    //localstorage mein update karenge
    setTheme(currentTheme);
    //remove the current theme

    if (oldTheme) {
        document.querySelector("html").classList.remove(oldTheme);
    }
    //set the current theme
    document.querySelector("html").classList.add(theme);

    // change the text of button
    document
        .querySelector("#theme_change_button")
        .querySelector("span").textContent = theme == "light" ? "Dark" : "Light";
}

//change page change theme