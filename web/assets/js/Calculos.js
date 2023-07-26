function calcularRolDePagos() {
    // Obtain the existing total to add the new amount
    let totalAPagar = parseFloat(document.getElementById("totalPagar").textContent);

    // Get the values entered by the user
    const horasTrabajadas = parseFloat(document.querySelector("input[name='detHoras']").value);
    const valorPorHora = parseFloat(document.querySelector("input[name='detHoraValor']").value);
    const montoAPagar = horasTrabajadas * valorPorHora;

    // Append the new row with the calculated values to the table
    const tablaDetalle = document.getElementById("detalle-table-body");
    const newRow = document.createElement("tr");
    newRow.innerHTML = `
            <td>${document.getElementById("detInicio").value}</td>
            <td>${document.getElementById("detFin").value}</td>
            <td>${horasTrabajadas}</td>
            <td>${valorPorHora}</td>
            <td>${montoAPagar.toFixed(2)}</td>
        `;
    tablaDetalle.appendChild(newRow);

    // Update the total to pay
    totalAPagar += montoAPagar;
    document.getElementById("totalPagar").textContent = totalAPagar.toFixed(2);
}
let selectedClient = null;
// Agregar un evento delegado a la tabla para manejar el clic en las filas
document.querySelector("#example tbody").addEventListener("click", function (event) {
    const target = event.target;
    if (target.tagName === "TD") {
        const row = target.parentElement;
        selectClient(row);
    }
});
function selectClient(row) {
    const cells = row.cells;
    selectedClient = {
        id: cells[0].innerText,
        name: cells[1].innerText + " " + cells[2].innerText,
    };
    document.getElementById('nombres').value = selectedClient.name;
    document.getElementById('fkUsuario').value = selectedClient.id;
}