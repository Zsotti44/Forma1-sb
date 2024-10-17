 function submitForm(event) {
    event.preventDefault();
    // Beolvassuk az űrlap értékeit
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    // Alapvető hitelesítési formátum: "username:password" -> Base64 kódolás
    const credentials = btoa(username + ":" + password);

    // Létrehozzuk a HTTP kérést a Base64-kódolt hitelesítési adatokkal
    fetch("/auth/login", {
    method: "POST",
    headers: {
    "Authorization": "Basic " + credentials,
    "Content-Type": "application/json"
},
    body: JSON.stringify({ username, password })  // Ha szükséges, küldjük el az adatokat a body-ban is
})
    .then(response => {
    if (response.ok) {
    console.log(response);
} else {
    console.error("Login failed!");
}
})
    .catch(error => console.error("Error:", error));
}