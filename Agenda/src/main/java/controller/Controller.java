package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.ContatosDAO;
import model.JavaModel;

// TODO: Auto-generated Javadoc
/**
 * The Class Controller.
 */
@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete", "/relatorio" })
public class Controller extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The dao. */
	ContatosDAO dao = new ContatosDAO();
	
	/** The Set contato model. */
	JavaModel SetContatoModel = new JavaModel();

	/**
	 * Instantiates a new controller.
	 */
	public Controller() {
		super();
}
    
    /**
     * Do get.
     *
     * @param request the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		String action = request.getServletPath();
		if (action.equals("/main")) {
			contatos(request, response);
		} else if (action.equals("/insert")) {
			NovoContato(request, response);
		} else if (action.equals("/select")) {
			ListarContato(request, response);
		} else if (action.equals("/update")) {
			EditarContato(request, response);
		} else if (action.equals("/delete")) {
			removerContato(request, response);
		} else if (action.equals("/relatorio")) {
			relatorio(request, response);

		} else {
			response.sendRedirect("index.html");
		}
	}

	/**
	 * Contatos.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<JavaModel> lista = dao.listarCadastro();
		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("contatos.jsp");
		rd.forward(request, response);
	} 
	
	/**
	 * Novo contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void NovoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SetContatoModel.setNome(request.getParameter("nome"));
		SetContatoModel.setFone(request.getParameter("fone"));
		SetContatoModel.setEmail(request.getParameter("email"));
		dao.inserirCadastro(SetContatoModel);
		response.sendRedirect("main");
	}
	
	/**
	 * Listar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void ListarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SetContatoModel.setIdcon(request.getParameter("idcon"));
		dao.selecionaCadastro(SetContatoModel);
		request.setAttribute("idcon", SetContatoModel.getIdcon());
		request.setAttribute("nome", SetContatoModel.getNome());
		request.setAttribute("email", SetContatoModel.getEmail());
		request.setAttribute("fone", SetContatoModel.getFone());
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}

	/**
	 * Editar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void EditarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SetContatoModel.setIdcon(request.getParameter("idcon"));
		SetContatoModel.setNome(request.getParameter("nome"));
		SetContatoModel.setFone(request.getParameter("fone"));
		SetContatoModel.setEmail(request.getParameter("email"));
		dao.alterarContato(SetContatoModel);
		response.sendRedirect("main");
	}

	
	/**
	 * Remover contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void removerContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SetContatoModel.setIdcon(request.getParameter("idcon"));
		dao.deletarContato(SetContatoModel);
		response.sendRedirect("main");
	}
	 
	/**
	 * Relatorio.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void relatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Document documento = new Document(PageSize.A4);
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "inline; filename=" + "contatos.pdf");
		
		try {response.reset();
			PdfWriter.getInstance(documento, response.getOutputStream());
			documento.setMargins(5f, 0f, 15f,0f);
			
			documento.open();
			
			Image imagem = Image.getInstance("C:\\Users\\ton\\eclipse-workspace\\ProgetoCrud\\src\\main\\webapp\\imagens\\logoC.png");
			imagem.scaleAbsolute(30f, 30f);
			imagem.setAlignment(Element.ALIGN_LEFT);
			documento.add(imagem);
			Paragraph tituloDocumento = new Paragraph("AGENDA DE CONTATOS");
			documento.add(tituloDocumento);
            documento.add(new Paragraph("Relatorio - Lista de Contatos"));
			documento.add(new Paragraph(" "));
			
			PdfPTable tabela = new PdfPTable(3);
			tabela.setTotalWidth(200f);
			tabela.setWidths(new float [] {60f, 30f, 60f});
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Email"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			ArrayList<JavaModel> lista = dao.listarCadastro();
			for (int i =0; i<lista.size();i++) {
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(lista.get(i).getFone());
				tabela.addCell(lista.get(i).getEmail());
			}
			documento.add(tabela);
			documento.close();
			
		} catch (Exception e ) {
			System.out.println(e);
			documento.close();
		}
	}
}
