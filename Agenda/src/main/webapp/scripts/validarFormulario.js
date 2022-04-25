/**
 * validar formulario
 * @author Herberton Lauro 
 */

function Validar() {
	var nome = FormCadastro.nome.value
	var fone = FormCadastro.fone.value
	if (nome === "") {
		alert('ops!!! Faltou o Nome ')
		FormCadastro.nome.focus()
		return false
	} else if (fone === "") {
		alert('ops!!! Faltou o telefone ')
		FormCadastro.fone.focus()
		return false
	} else {
		document.forms["FormCadastro"].submit()
	}
}