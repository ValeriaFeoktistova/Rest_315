async function getNavInfo() {
    fetch('http://localhost:8081/api/user/info')
        .then(res => res.json())
        .then(user => getInfo(user))
}

function getInfo(user) {
    document.getElementById('userEmail').textContent = user.mail;
    document.getElementById('userRoles').textContent = getRolesString(user.roles);
}

getNavInfo()