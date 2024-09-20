// Save scroll position before submitting the form
function saveScrollPosition() {
    localStorage.setItem('scrollPosition', window.scrollY);
}

// Restore the scroll position when the page loads
window.onload = function() {
    var scrollPosition = localStorage.getItem('scrollPosition');
    if (scrollPosition !== null) {
        window.scrollTo(0, parseInt(scrollPosition));
    }
};
