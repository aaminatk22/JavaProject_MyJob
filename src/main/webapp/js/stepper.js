document.addEventListener("DOMContentLoaded", () => {
    // Ensure the first step is visible when the page loads
    goToStep(1);
});

// Function to navigate to a specific step
function goToStep(step) {
    // Debugging log to ensure the function is triggered
    console.log(`Navigating to step ${step}`);

    // Hide all steps
    document.querySelectorAll(".step").forEach(el => el.classList.add("hidden"));

    // Show the current step
    const currentStep = document.getElementById(`step-${step}`);
    if (currentStep) {
        currentStep.classList.remove("hidden");
    } else {
        console.error(`Step ${step} not found.`);
    }

    // Update the progress bar
    updateProgressBar(step);
}

// Function to update the progress bar
function updateProgressBar(step) {
    const progress = (step - 1) * 25; // 4 steps = 100%
    const progressBar = document.getElementById("progress-bar");
    if (progressBar) {
        progressBar.style.width = `${progress}%`;
    } else {
        console.error("Progress bar element not found.");
    }
}

// Function to add items to a list (e.g., Projects, Skills, Experiences)
function addToList(containerId, inputId) {
    const input = document.getElementById(inputId);
    if (!input) {
        console.error(`Input element with ID '${inputId}' not found.`);
        return;
    }

    const value = input.value.trim();
    if (value) {
        const list = document.getElementById(containerId);
        if (list) {
            const listItem = document.createElement("li");
            listItem.className = "p-2 border rounded-lg bg-gray-100 my-2";
            listItem.textContent = value;
            list.appendChild(listItem);
            input.value = ""; // Clear the input field
        } else {
            console.error(`Container with ID '${containerId}' not found.`);
        }
    }
}
