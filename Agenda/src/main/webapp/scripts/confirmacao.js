/**
 * Confirmação de excluir 
 * @author Herberton Lauro
 * @param idcon
 */
 function confirmExclusao(idcon) {
   var resposta = confirm("Tem certeza que deseja excluir esse contato?")
   if (resposta === true) {
	  window.location.href = "delete?idcon=" + idcon
   }
}