var count = 0;

function addFields(){
    count ++;
    var newFields = document.getElementById('answer').cloneNode(true);
    // newFields.id = count;
    // newFields.style.display = 'block';
    var newField = newFields.childNodes;
    for(var i=0; i<newField.length; i++){
        var theName = newField[i].name;
        if(theName){
            newField[i].name = theName + count;
        }
    }
    var insertHere = document.getElementById("addQuestion");
    insertHere.parentNode.insertBefore(newField, insertHere);
}

window.onload = addFields;