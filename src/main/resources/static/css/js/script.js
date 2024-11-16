console.log("Script loaded");

// Get the initial theme
let currentTheme = getTheme();

document.addEventListener("DOMContentLoaded", () => {
    changePageTheme(currentTheme, "");
    initializeThemeChangeListener();
});

function initializeThemeChangeListener() {
    const changeThemeButton = document.querySelector("#theme_change_button");

    // Check if the event listener is already added
    if (!changeThemeButton.dataset.listener) {
        changeThemeButton.addEventListener("click", () => {
            console.log("Change theme button clicked");

            const oldTheme = currentTheme;
            currentTheme = currentTheme === "dark" ? "light" : "dark";

            console.log("New theme:", currentTheme);
            changePageTheme(currentTheme, oldTheme);
        });

        // Mark the button as having a listener
        changeThemeButton.dataset.listener = true;
    }
}

function setTheme(theme) {
    localStorage.setItem("theme", theme);
}

function getTheme() {
    let theme = localStorage.getItem("theme");
    return theme ? theme : "light";
}

function changePageTheme(theme, oldTheme) {
    setTheme(theme);

    // Remove the previous theme
    if (oldTheme) {
        document.querySelector("html").classList.remove(oldTheme);
    }

    // Add the new theme
    document.querySelector("html").classList.add(theme);

    // Update button text
    document
        .querySelector("#theme_change_button span")
        .textContent = theme === "light" ? "Dark" : "Light";
}
