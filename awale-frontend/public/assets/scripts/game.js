let row = document.querySelectorAll('.user>div');

row.forEach(child => {
  child.addEventListener('click', handleClick)
});

function handleClick(e) {
  let jsonClick = {"hole": null, "value": null};
  jsonClick.hole = e.target.className.slice(-1);
  jsonClick.value = e.target.innerHTML;
  console.log(JSON.stringify(jsonClick));
}