const courseNames = {
    fundamentals: 'Java Fundamentals',
    advanced: 'Java Advanced',
    db: 'Java DB',
    web: 'Java Web',
    htmlAndCSS: 'HTML & CSS',
}
const availableCourses = [
    { name: courseNames.fundamentals, price: 170},
    { name: courseNames.advanced, price: 180},
    { name: courseNames.db, price: 190},
    { name: courseNames.web, price: 490}
];

const educationFormNames = {
    online: 'Online',
    onSite: 'On site'
}

const educationTypes = [
    { name: educationFormNames.onSite, discount: 0 },
    { name: educationFormNames.online, discount: 0.06 }
];


const getCourseItem = (course) => {
   return $('<label/>')
        .append(
            $('<input/>')
                .attr('type', 'checkbox')
                .val(course.name)
            )
            .append(course.name)
}

const getMyCourseItem = (course) => course.name;

const getEducationalItem = (educationalForm) => {
    return $('<label/>')
            .append(educationalForm.name)
                .append(
                    $('<input/>')
                        .attr('type', 'radio')
                        .attr('name', 'education-form')
                        .val(educationalForm.name)
                )
}

const generateList = (item, getItemFunc) => 
     item
    .map(item => getItemFunc(item))
        .map(itemElement => $('<li/>')
                    .append(itemElement)
            )
        


const generateAvailableCoursesList = () => {
    const courseItems = generateList(availableCourses, getCourseItem);
    courseItems.forEach(course => course.appendTo('#list-courses'));
};

const generateMyCoursesList = (selectedCourses) => {
    const courseItems = generateList(selectedCourses, getMyCourseItem);
    $('#list-my-courses')
        .html('');
    courseItems.forEach(course => course.appendTo('#list-my-courses'));
};

const generateEducationForms = () => {
    const educationalItem = generateList(educationTypes, getEducationalItem);
    educationalItem.forEach(educationalItem => educationalItem.appendTo('#list-educational-type'));
}

const getSelectedCourses = () => {
    const courseNames = Array.from($('#list-courses input:checked'))
    .map(input => $(input).val());
    return courseNames.map(courseName => 
        ({...availableCourses.find(course => course.name === courseName)}))
}

const getSelectedEducationForm = () => {
    const educationFormName = $('#list-educational-type input:checked').val();
    return educationTypes
            .find(educationType => educationType.name === educationFormName);
}
const getCourse = (selectedCourses, courseName) => {
    return selectedCourses.find(course => course.name === courseName);
}

const decorateCourses = (selectedCourses) => {
    const fundamentalsCourse = getCourse(selectedCourses, courseNames.fundamentals);
    const advancedCourse = getCourse(selectedCourses, courseNames.advanced);
    const dbCourse = getCourse(selectedCourses, courseNames.db);
    const webCourse = getCourse(selectedCourses, courseNames.web);

    if(fundamentalsCourse && advancedCourse) {
        // discount 10%
        advancedCourse.price *= 0.9;
        if(dbCourse) {
            //discount 6%
            fundamentalsCourse.price *= 0.94;
            advancedCourse.price *= 0.94;
            dbCourse.price *= 0.94;
            if(webCourse) {
                selectedCourses.push({
                    name: courseNames.htmlAndCSS, price: 0
                });
            }
        }
    }
  
}
    
const onSignMeUpClick = () => {
    const selectedCourses = getSelectedCourses();
    const selectedEducationForm = getSelectedEducationForm();
    decorateCourses(selectedCourses);
    let totalPrice = selectedCourses.reduce((sum, course) => sum + course.price, 0);
    if(selectedEducationForm === educationFormNames.online) {
        //discount 6%
        totalPrice *= 0.94;
    }
    $('#total-price').html(totalPrice.toFixed(2));
    generateMyCoursesList(selectedCourses);

};

$(function() {
    generateAvailableCoursesList();
    generateEducationForms();

    $('#btn-sign-me-up')
        .on('click', onSignMeUpClick);
});