package com.boutique.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.boutique.dao.DaoBoutique;
import com.boutique.dao.DaoInventarios;
import com.boutique.dao.DaoTipoProductos;
import com.boutique.domain.Boutique;
import com.boutique.domain.MarcaDescripcion;
import com.boutique.domain.TipoProducto;
import com.boutique.domain.inventarios.Unidad;
import com.boutique.engine.impl.AppInstance;
import com.boutique.engine.impl.RegistroInventarioEngine;
import com.boutique.helper.IvaHelper;

public class FrmAgregarProductoNuevo extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String UNIDAD_DEFAULT = "NO APLICABLE";
	String accion;
	RegistroInventarioEngine engine;
	BorderLayout borderLayout1 = new BorderLayout();
	JLabel jLabel1 = new JLabel();
	JSplitPane jSplitPane1 = new JSplitPane();
	JPanel jPanel1 = new JPanel();
	GridLayout gridLayout1 = new GridLayout();
	JLabel jLabel2 = new JLabel();
	JComboBox cmbTipoProducto = new JComboBox();
	JComboBox cmbUnidad = new JComboBox();

	JScrollPane jScrollPane1 = new JScrollPane();
	JTable tblCantidadPorTalla = new JTable();
	JLabel lblPrecioCosto = new JLabel();
	JLabel lblIvaPrecioCosto = new JLabel();
	JLabel lblGasto = new JLabel();
	JLabel lblIvaGasto = new JLabel();
	JLabel lblCostoReal = new JLabel();

	JPanel pnlPosiblesPreciosPublico = new JPanel();
	GridLayout gridPrecioPublic = new GridLayout();
	JPanel pnlPrecioCosto = new JPanel();
	GridLayout gridPrecioCosto = new GridLayout();
	JPanel pnlPrecioGasto = new JPanel();
	GridLayout gridPrecioGasto = new GridLayout();
	JPanel pnlPrecioCostoReal = new JPanel();
	GridLayout gridPrecioCostoReal = new GridLayout();

	GridLayout gridPrecioPublicoFinal = new GridLayout();
	JPanel pnlPrecioPublicoFinal = new JPanel();

	JLabel jLabel5 = new JLabel();
	JTextField txtCosto140 = new JTextField();
	JLabel jLabel6 = new JLabel();
	JLabel jLabel100 = new JLabel();
	JTextField txtCosto120 = new JTextField();
	JLabel jLabel7 = new JLabel();
	JTextField txtCosto100 = new JTextField();
	JLabel jLabel8 = new JLabel();
	JTextField txtCosto80 = new JTextField();
	JTextField txtPrecioCostoInicial = new JTextField();
	JTextField txtIvaPrecioCosto = new JTextField();
	JTextField txtGasto = new JTextField();
	JTextField txtIvaGasto = new JTextField();
	JTextField txtCostoReal = new JTextField();

	JLabel lblPPFinal = new JLabel();
	JLabel lblPPFinalIva = new JLabel();
	JLabel lblPPFinalTotal = new JLabel();

	JTextField txtPrecioPublicoFinal = new JTextField();
	JTextField txtPrecioPublicoFinalIva = new JTextField();
	JTextField txtPrecioPublicoFinalTotal = new JTextField();

	java.util.List<TipoProducto> listaTipoProductos = DaoTipoProductos
			.findAll();
	java.util.List<Boutique> listaBoutiques = DaoBoutique.findAll();
	ModelProductosPorTalla modelProductosPorTalla = new ModelProductosPorTalla();
	NumberFormat numberFormat;
	JPanel jPanel3 = new JPanel();
	JButton cmdAgregar = new JButton();
	JButton cmdLimpiar = new JButton();
	JButton cmdCancelar = new JButton();

	public FrmAgregarProductoNuevo() {
		try {
			jbInit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		getContentPane().setLayout(borderLayout1);
		jLabel1.setToolTipText("");
		jLabel1.setText("Agregar producto nuevo");
		lblPrecioCosto.setToolTipText("");
		lblPrecioCosto.setText("Precio al costo");
		lblIvaPrecioCosto.setText("IVA");
		lblGasto.setText("Gasto");
		lblIvaGasto.setText("IVA");
		lblCostoReal.setText("Costo real:");
		jLabel100.setText("Unidad:");
		pnlPosiblesPreciosPublico.setLayout(gridPrecioPublic);
		gridPrecioPublic.setColumns(4);
		gridPrecioPublic.setRows(2);
		pnlPrecioCosto.setLayout(gridPrecioCosto);
		gridPrecioCosto.setColumns(2);
		gridPrecioCosto.setRows(2);

		pnlPrecioGasto.setLayout(gridPrecioGasto);
		gridPrecioGasto.setColumns(2);
		gridPrecioGasto.setRows(2);

		pnlPrecioCostoReal.setLayout(gridPrecioCostoReal);
		gridPrecioCostoReal.setColumns(2);
		gridPrecioCostoReal.setRows(1);

		pnlPrecioPublicoFinal.setLayout(gridPrecioPublicoFinal);
		gridPrecioPublicoFinal.setRows(2);
		gridPrecioPublicoFinal.setColumns(3);

		lblPPFinal.setText("Precio al publico final:");
		txtPrecioPublicoFinal.setText("");
		lblPPFinalIva.setText("Iva:");
		txtPrecioPublicoFinalIva.setText("");
		lblPPFinalTotal.setText("PP IVA Incluido:");
		txtPrecioPublicoFinalTotal.setText("");

		pnlPrecioPublicoFinal.add(lblPPFinal);
		pnlPrecioPublicoFinal.add(lblPPFinalIva);
		pnlPrecioPublicoFinal.add(lblPPFinalTotal);

		pnlPrecioPublicoFinal.add(txtPrecioPublicoFinal);

		pnlPrecioPublicoFinal.add(txtPrecioPublicoFinalIva);
		pnlPrecioPublicoFinal.add(txtPrecioPublicoFinalTotal);

		txtIvaGasto.setEditable(false);
		txtIvaPrecioCosto.setEditable(false);
		txtPrecioPublicoFinalIva.setEditable(false);
		txtCostoReal.setEditable(false);
		jLabel5.setText("Costo al 80%");
		txtCosto140.setEnabled(false);
		txtCosto140.setForeground(Color.black);
		txtCosto140.setDisabledTextColor(Color.black);
		txtCosto140.setText("");
		jLabel6.setText("Costo al 120%");
		txtCosto120.setEnabled(false);
		txtCosto120.setDisabledTextColor(Color.black);
		txtCosto120.setText("");
		jLabel7.setText("Costo al 140%");
		txtCosto100.setEnabled(false);
		txtCosto100.setDisabledTextColor(Color.black);
		txtCosto100.setText("");
		jLabel8.setToolTipText("");
		jLabel8.setText("Costo al 100%");
		txtCosto80.setEnabled(false);
		txtCosto80.setDisabledTextColor(Color.black);
		txtCosto80.setText("");
		txtPrecioCostoInicial.setText("");

		/*
		 * jTextField.addKeyListener(new java.awt.event.KeyAdapter() { public
		 * void keyTyped(java.awt.event.KeyEvent e) {
		 * System.out.println("keyTyped()"); // TODO Auto-generated Event stub
		 * keyTyped() } });
		 */

		txtPrecioCostoInicial
				.addActionListener(new FrmAgregarProductoNuevo_txtPrecioCosto_actionAdapter(
						this));
		txtGasto.addActionListener(new FrmAgregarProductoNuevo_txtPrecioCosto_actionAdapter(
				this));

		txtPrecioCostoInicial
				.addFocusListener(new java.awt.event.FocusAdapter() {
					@Override
					public void focusLost(java.awt.event.FocusEvent e) {
						calcularCosto();
					}
				});
		txtGasto.addFocusListener(new java.awt.event.FocusAdapter() {
			@Override
			public void focusLost(java.awt.event.FocusEvent e) {
				calcularCosto();
			}
		});

		txtPrecioPublicoFinal
				.addFocusListener(new java.awt.event.FocusAdapter() {
					@Override
					public void focusLost(java.awt.event.FocusEvent e) {
						calcularPreciosAlPublico();
					}
				});
		txtPrecioPublicoFinal
				.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(java.awt.event.ActionEvent e) {
						calcularPreciosAlPublico();
					}
				});

		txtPrecioPublicoFinalTotal
				.addFocusListener(new java.awt.event.FocusAdapter() {
					@Override
					public void focusLost(java.awt.event.FocusEvent e) {
						calcularPreciosAlPublicoDesdePrecioTotal();
					}
				});
		txtPrecioPublicoFinalTotal
				.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(java.awt.event.ActionEvent e) {
						calcularPreciosAlPublicoDesdePrecioTotal();
					}
				});

		cmbTipoProducto
				.addActionListener(new FrmAgregarProductoNuevo_cmbTipoProducto_actionAdapter(
						this));

		cmbUnidad
				.addActionListener(new FrmAgregarProductoNuevo_cmbUnidad_actionAdapter(
						this));

		tblCantidadPorTalla.setModel(modelProductosPorTalla);
		cmdAgregar.setText("Agregar");
		cmdAgregar
				.addMouseListener(new FrmAgregarProductoNuevo_cmdAgregar_mouseAdapter(
						this));
		cmdLimpiar.setText("Limpiar");
		cmdLimpiar
				.addActionListener(new FrmAgregarProductoNuevo_cmdLimpiar_actionAdapter(
						this));
		cmdCancelar.setText("Cancelar");
		cmdCancelar
				.addMouseListener(new FrmAgregarProductoNuevo_cmdCancelar_mouseAdapter(
						this));
		this.setTitle("Inventario inicial");
		this.addWindowListener(new FrmAgregarProductoNuevo_this_windowAdapter(
				this));
		pnlDatosProducto.setLayout(gridLayout3);
		gridLayout3.setColumns(8);
		gridLayout3.setRows(2);
		jLabel3.setText("Estilo");
		jLabel10.setText("Marca:");
		jLabel11.setText("Color:");
		jLabel12.setText("Descripción:");
		txtEstilo.setEditable(true);
		cmbMarca.setEditable(true);
		cmbMarca.addActionListener(new FrmAgregarProductoNuevo_cmbEstilo2_actionAdapter(
				this));

		cmbDescripcion.setEditable(true);
		cmbDescripcion
				.addActionListener(new FrmAgregarProductoNuevo_cmbEstilo3_actionAdapter(
						this));
		cmbColor.setEditable(true);
		cmbColor.addActionListener(new FrmAgregarProductoNuevo_cmbColor_actionAdapter(
				this));
		this.getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);
		jSplitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		jPanel1.setLayout(gridLayout1);
		gridLayout1.setColumns(1);
		gridLayout1.setRows(10);
		jLabel2.setText("Tipo de producto:");
		jPanel1.add(jLabel2);
		jPanel1.add(cmbTipoProducto);
		jPanel1.add(jLabel100);
		jPanel1.add(cmbUnidad);
		jPanel1.add(pnlDatosProducto);
		pnlDatosProducto.add(jLabel3);
		pnlDatosProducto.add(jLabel10);
		pnlDatosProducto.add(jLabel12);
		pnlDatosProducto.add(jLabel11);
		pnlDatosProducto.add(txtEstilo);
		pnlDatosProducto.add(cmbMarca);
		pnlDatosProducto.add(cmbDescripcion);
		pnlDatosProducto.add(cmbColor);
		jPanel1.add(pnlPrecioCosto);
		jPanel1.add(pnlPrecioGasto);
		jPanel1.add(pnlPrecioCostoReal);
		pnlPrecioCosto.add(lblPrecioCosto);
		pnlPrecioCosto.add(lblIvaPrecioCosto);
		pnlPrecioCosto.add(txtPrecioCostoInicial);
		pnlPrecioCosto.add(txtIvaPrecioCosto);
		pnlPrecioGasto.add(lblGasto);
		pnlPrecioGasto.add(lblIvaGasto);
		pnlPrecioGasto.add(txtGasto);
		pnlPrecioGasto.add(txtIvaGasto);
		pnlPrecioCostoReal.add(lblCostoReal);
		pnlPrecioCostoReal.add(txtCostoReal);

		jPanel1.add(pnlPosiblesPreciosPublico);
		jPanel1.add(pnlPrecioPublicoFinal);
		pnlPosiblesPreciosPublico.add(jLabel5);
		pnlPosiblesPreciosPublico.add(jLabel8);
		pnlPosiblesPreciosPublico.add(jLabel6);
		pnlPosiblesPreciosPublico.add(jLabel7);
		pnlPosiblesPreciosPublico.add(txtCosto80);
		pnlPosiblesPreciosPublico.add(txtCosto100);
		pnlPosiblesPreciosPublico.add(txtCosto120);
		pnlPosiblesPreciosPublico.add(txtCosto140);
		jSplitPane1.add(jScrollPane1, JSplitPane.RIGHT);
		jScrollPane1.getViewport().add(tblCantidadPorTalla);
		jSplitPane1.add(jPanel1, JSplitPane.LEFT);
		this.getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);
		jPanel3.add(cmdAgregar);
		jPanel3.add(cmdLimpiar);
		jPanel3.add(cmdCancelar);
		numberFormat = NumberFormat.getNumberInstance(java.util.Locale.ENGLISH);
		numberFormat.setMinimumFractionDigits(2);
		numberFormat.setMaximumFractionDigits(2);
		numberFormat.setGroupingUsed(false);
		this.getContentPane().add(jLabel1, java.awt.BorderLayout.NORTH);
		for (TipoProducto tp : listaTipoProductos) {
			this.cmbTipoProducto.addItem(tp.getNombre());
		}

	}

	public void cmbTipoProducto_actionPerformed(ActionEvent e) {
		// Dependiendo del tipo de talla es la el modelo de tallsa que ponemos
		// en la tabla
		TipoProducto p = this.listaTipoProductos.get(this.cmbTipoProducto
				.getSelectedIndex());
		// Tenemos el tipo de producto // vemos que tipo de talla y lo ponemos
		// en la tablita
		this.modelProductosPorTalla.setTipoTallas(p.getTipoTalla(), null);
		// Actualizamos el combo box de las unidades.
		this.cmbUnidad.removeAllItems();

		cmbUnidad.addItem(UNIDAD_DEFAULT);
		if (CollectionUtils.isNotEmpty(p.getUnidades())) {
			for (Unidad unidad : p.getUnidades()) {
				cmbUnidad.addItem(unidad.getDescripcion());
			}
		}

	}

	private void calcularPreciosAlPublicoDesdePrecioTotal() {
		// Obtener precio al publico inicial
		double ppFinalTotal = 0;

		// Revisar que precio final no este en blanco, sino, llamar a
		// calcularPreciosAlPublico

		if (!StringUtils.isBlank(txtPrecioPublicoFinalTotal.getText())) {
			// Revisamos que sea numero
			try {
				numberFormat.parse(txtPrecioPublicoFinalTotal.getText());
				ppFinalTotal = Double.parseDouble(txtPrecioPublicoFinalTotal
						.getText());
				// Desglosar IVA y asignar
				double precioFinalSinIva = IvaHelper
						.getMontoSinIva(ppFinalTotal);

				txtPrecioPublicoFinalIva.setText(numberFormat.format(IvaHelper
						.getIvaDesglosadFromMonto(ppFinalTotal)));

				// Asignar precio final
				txtPrecioPublicoFinal.setText(numberFormat
						.format(precioFinalSinIva));

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null,
						"Introduzca un formato de numero valido", "Atencion",
						JOptionPane.ERROR_MESSAGE);
				System.out.println(ex.toString());
			}

		} else {
			calcularPreciosAlPublico();
		}
	}

	public void cmbUnidad_actionPerformed(ActionEvent e) {
		// Obtener el tipo de producto para despues la unidad seleccionada
		TipoProducto p = this.listaTipoProductos.get(this.cmbTipoProducto
				.getSelectedIndex());
		if (cmbUnidad.getSelectedIndex() > 0) {

			// Tenemos el tipo de producto // vemos que tipo de talla y lo
			// ponemos
			// en la tablita

			this.modelProductosPorTalla.setTipoTallas(p.getTipoTalla(), p
					.getUnidades().get(cmbUnidad.getSelectedIndex() - 1));
		} else {
			this.modelProductosPorTalla.setTipoTallas(p.getTipoTalla(), null);

		}
	}

	public void cmdLimpiar_actionPerformed(ActionEvent e) {
		limpiarCasillas();
	}

	/**
	 * limpiarCasillas
	 */
	private void limpiarCasillas() {
		cmbTipoProducto.setSelectedIndex(0);
		txtPrecioCostoInicial.setText("");
		txtCosto80.setText("");
		txtCosto100.setText("");
		txtCosto120.setText("");
		txtCosto140.setText("");
		txtPrecioPublicoFinal.setText("");
		txtEstilo.setText("");
		cmbMarca.setSelectedIndex(0);
		cmbDescripcion.setSelectedIndex(0);
		cmbColor.setSelectedIndex(0);
		txtIvaGasto.setText("");
		txtIvaPrecioCosto.setText("");
		txtCostoReal.setText("");
		txtPrecioPublicoFinalIva.setText("");
		txtPrecioPublicoFinalTotal.setText("");
		txtGasto.setText("");
	}

	public void calcularCosto() {
		// Revisar si hay costo indicado
		double costo = 0;
		double gasto = 0;
		if (!StringUtils.isBlank(txtPrecioCostoInicial.getText())) {
			// Revisamos que sea numero
			try {
				numberFormat.parse(txtPrecioCostoInicial.getText());
				costo = Double.parseDouble(txtPrecioCostoInicial.getText());

				// Cacular IVA y asignar
				txtIvaPrecioCosto.setText(numberFormat.format(IvaHelper
						.getIvaFromMontoInicial(costo)));
				costo += Double.parseDouble(txtIvaPrecioCosto.getText());

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null,
						"Introduzca un formato de numero valido", "Atencion",
						JOptionPane.ERROR_MESSAGE);
				System.out.println(ex.toString());
				txtPrecioCostoInicial.setText("");
				txtPrecioCostoInicial.requestFocus();
			}

		}
		// Revisar si hay gasto indicado

		if (!StringUtils.isBlank(txtGasto.getText())) {
			// Revisamos que sea numero
			try {
				numberFormat.parse(txtGasto.getText());
				gasto = Double.parseDouble(txtGasto.getText());

				// Cacular IVA y asignar
				txtIvaGasto.setText(numberFormat.format(IvaHelper
						.getIvaFromMontoInicial(gasto)));
				gasto += Double.parseDouble(txtIvaGasto.getText());

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null,
						"Introduzca un formato de numero valido", "Atencion",
						JOptionPane.ERROR_MESSAGE);
				System.out.println(ex.toString());
				txtGasto.setText("");
				txtGasto.requestFocus();
			}

		}

		// Sumar todo y poner Costo real
		double costoReal = costo + gasto;
		txtCostoReal.setText(numberFormat.format(costoReal));
		// calcular precios al publico

		txtCosto80.setText(numberFormat.format(costoReal * 1.80));
		txtCosto100.setText(numberFormat.format(costoReal * 2));
		txtCosto120.setText(numberFormat.format(costoReal + (costoReal * 1.2)));
		txtCosto140.setText(numberFormat.format(costoReal + (costoReal * 1.4)));
		txtPrecioPublicoFinal.setText(numberFormat.format(costoReal * 2));
		calcularPreciosAlPublico();
	}

	public void calcularPreciosAlPublico() {
		// Obtener precio al publico inicial
		double ppFinal = 0;

		if (!StringUtils.isBlank(txtPrecioPublicoFinal.getText())) {
			// Revisamos que sea numero
			try {
				numberFormat.parse(txtPrecioPublicoFinal.getText());
				ppFinal = Double.parseDouble(txtPrecioPublicoFinal.getText());

				// Cacular IVA y asignar
				txtPrecioPublicoFinalIva.setText(numberFormat.format(IvaHelper
						.getIvaFromMontoInicial(ppFinal)));
				ppFinal += Double.parseDouble(txtPrecioPublicoFinalIva
						.getText());

				// Asignar precio total
				txtPrecioPublicoFinalTotal
						.setText(numberFormat.format(ppFinal));

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null,
						"Introduzca un formato de numero valido", "Atencion",
						JOptionPane.ERROR_MESSAGE);
				System.out.println(ex.toString());
				txtPrecioPublicoFinal.setText("");
				txtPrecioPublicoFinal.requestFocus();
			}

		}

	}

	public void txtPrecioCosto_actionPerformed(ActionEvent e) {
		calcularCosto();
	}

	public void cmdAgregar_mouseClicked(MouseEvent e) {
		try {
			this.setCursor(com.boutique.engine.impl.AppInstance.waitCursor);

			// Revisamos los precios de costo (costo sin iva, costo_iva, gasto,
			// gasto_iva, costo real)
			// Revisamos los precios al publico (p inicial, p inicial_iva,
			// p_inicial_total, p_final, p_final_iva, p_final_total)

			double precioCostoInicial = numberFormat.parse(
					txtPrecioCostoInicial.getText()).doubleValue();

			double impuestoPrecioCosto = numberFormat.parse(
					txtIvaPrecioCosto.getText()).doubleValue();
			double gasto = numberFormat.parse(txtGasto.getText()).doubleValue();
			double impuestoGasto = numberFormat.parse(txtIvaGasto.getText())
					.doubleValue();
			double precioCosto = numberFormat.parse(txtCostoReal.getText())
					.doubleValue();

			double precioPublicoInicial = numberFormat.parse(
					txtPrecioPublicoFinal.getText()).doubleValue();

			double impuestoPrecioPublicoInicial = numberFormat.parse(
					txtPrecioPublicoFinalIva.getText()).doubleValue();
			double precioPublicoInicialTotal = numberFormat.parse(
					txtPrecioPublicoFinalTotal.getText()).doubleValue();
			double precioPublicoFinal = numberFormat.parse(
					txtPrecioPublicoFinal.getText()).doubleValue();
			double impuestoPrecioPublicoFinal = numberFormat.parse(
					txtPrecioPublicoFinalIva.getText()).doubleValue();
			double precioPublico = numberFormat.parse(
					txtPrecioPublicoFinalTotal.getText()).doubleValue();

			int idMarca;
			int idDescripcion;
			int idColor;
			// Revisamos que haya un tipo de producto seleccionado
			if (cmbTipoProducto.getSelectedIndex() >= 0) {
				if (this.modelProductosPorTalla.siHayProductos()) {
					// Agregamos los productos
					TipoProducto tp = listaTipoProductos
							.get(this.cmbTipoProducto.getSelectedIndex());
					// REVISAMOS LAS MARCAS DESCRIPCIONES Y COLORES

					if (this.cmbMarca.getSelectedIndex() < 0) { // ES UNA NUEVA
																// MARCA, LA
																// REGISTRAMOS Y
																// OBTENEMOS SU
																// ID
						MarcaDescripcion md = DaoInventarios
								.addMarca(this.cmbMarca.getSelectedItem()
										.toString());
						AppInstance.marcas2id.put(md.getMarcaDescripcion(),
								md.getId());
						AppInstance.id2marcas.put(md.getId(),
								md.getMarcaDescripcion());
						idMarca = md.getId();
						cmbMarca.addItem(md.getMarcaDescripcion());
					} else if (this.cmbMarca.getSelectedIndex() == 0) { // HAY
																		// UNA
																		// MARCA
																		// SELECCIONADA,
																		// LA
																		// PONEMOS
																		// EN LA
																		// LISTA
						idMarca = 0;
					} else {
						idMarca = AppInstance.marcas2id.get(cmbMarca
								.getSelectedItem().toString());
					}

					if (this.cmbDescripcion.getSelectedIndex() < 0) { // ES UNA
																		// NUEVA
																		// DESCRIPCION,
																		// LA
																		// REGISTRAMOS
																		// Y
																		// OBTENEMOS
																		// SU ID
						MarcaDescripcion md = DaoInventarios
								.addDescripcion(this.cmbDescripcion
										.getSelectedItem().toString());
						AppInstance.descripciones2id.put(
								md.getMarcaDescripcion(), md.getId());
						AppInstance.id2descripciones.put(md.getId(),
								md.getMarcaDescripcion());
						idDescripcion = md.getId();
						cmbDescripcion.addItem(md.getMarcaDescripcion());
					} else if (this.cmbDescripcion.getSelectedIndex() == 0) {
						idDescripcion = 0;
					} else {
						idDescripcion = AppInstance.descripciones2id
								.get(cmbDescripcion.getSelectedItem()
										.toString());
					}
					if (this.cmbColor.getSelectedIndex() < 0) { // ES UNA NUEVO
																// COLOR, LO
																// REGISTRAMOS Y
																// OBTENEMOS SU
																// ID
						MarcaDescripcion md = DaoInventarios
								.addColor(this.cmbColor.getSelectedItem()
										.toString());
						AppInstance.colores2id.put(md.getMarcaDescripcion(),
								md.getId());
						AppInstance.id2colores.put(md.getId(),
								md.getMarcaDescripcion());
						idColor = md.getId();
						cmbColor.addItem(md.getMarcaDescripcion());
					} else if (this.cmbColor.getSelectedIndex() == 0) {
						idColor = 0;
					} else {
						idColor = AppInstance.colores2id.get(cmbColor
								.getSelectedItem().toString());
					}

					if (this.engine.registrarProducto(tp, precioCostoInicial,
							impuestoPrecioCosto, gasto, impuestoGasto,
							precioCosto, precioPublicoInicial,
							impuestoPrecioPublicoInicial,
							precioPublicoInicialTotal, precioPublicoFinal,
							impuestoPrecioPublicoFinal, precioPublico,
							modelProductosPorTalla.data, txtEstilo.getText(),
							idMarca, idDescripcion, idColor)) {

						/*
						 * public boolean registrarProducto(TipoProducto tp,
						 * double precioCostoInicial, double
						 * impuestoPrecioCosto, double gasto, double
						 * impuestoGasto, double precioCosto, double
						 * precioPublicoInicial, double
						 * impuestoPrecioPublicoInicial, double
						 * precioPublicoInicialTotal, double precioPublicoFinal,
						 * double impuestoPrecioPublicoFinal, double
						 * precioPublico, List<?> listaProductos, String estilo,
						 * int idMarca, int idDescripcion, int idColor) {
						 */

						// Se agregaron los registros, refrescamos la ventana
						// grande y preguntamos si deseamos agregar otro
						// producto

						limpiarCasillas();

						String oldAccion;
						oldAccion = this.accion;
						this.accion = String.valueOf(this.engine
								.getProductosRegistrados().size());

						this.firePropertyChange("actualizarTabla", oldAccion,
								this.accion);
					} else {
						this.setCursor(com.boutique.engine.impl.AppInstance.defCursor);
						JOptionPane
								.showMessageDialog(
										this,
										"El producto no pudo ser registrado en el inventario inicial",
										"Error", JOptionPane.ERROR_MESSAGE);
					}
				}

			}
			this.setCursor(com.boutique.engine.impl.AppInstance.defCursor);
		}
		// Tenemos todos los productos del modelo registrados

		catch (ParseException ex) {
			this.setCursor(com.boutique.engine.impl.AppInstance.defCursor);
			JOptionPane.showMessageDialog(this, "Precios incorrectos",
					"Atencion", JOptionPane.ERROR_MESSAGE);
		}

	}

	public void cmdCancelar_mouseClicked(MouseEvent e) {
		this.limpiarCasillas();
		this.setVisible(false);
	}

	@SuppressWarnings("rawtypes")
	transient Vector propertyChangeListeners;
	JPanel pnlDatosProducto = new JPanel();
	GridLayout gridLayout3 = new GridLayout();
	JLabel jLabel3 = new JLabel();
	JLabel jLabel10 = new JLabel();
	JComboBox cmbColor = new JComboBox();
	JComboBox cmbDescripcion = new JComboBox();
	JLabel jLabel11 = new JLabel();
	JComboBox cmbMarca = new JComboBox();
	JLabel jLabel12 = new JLabel();
	JTextField txtEstilo = new JTextField();

	@Override
	@SuppressWarnings("rawtypes")
	public synchronized void removePropertyChangeListener(
			PropertyChangeListener l) {
		super.removePropertyChangeListener(l);
		if (propertyChangeListeners != null
				&& propertyChangeListeners.contains(l)) {
			Vector v = (Vector) propertyChangeListeners.clone();
			v.removeElement(l);
			propertyChangeListeners = v;
		}
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public synchronized void addPropertyChangeListener(PropertyChangeListener l) {
		super.addPropertyChangeListener(l);
		Vector v = propertyChangeListeners == null ? new Vector(2)
				: (Vector) propertyChangeListeners.clone();
		if (!v.contains(l)) {
			v.addElement(l);
			propertyChangeListeners = v;
		}
	}

	@SuppressWarnings("rawtypes")
	protected void firePropertyChange(PropertyChangeEvent e) {
		if (propertyChangeListeners != null) {
			Vector listeners = propertyChangeListeners;
			int count = listeners.size();
			for (int i = 0; i < count; i++) {
				((PropertyChangeListener) listeners.elementAt(i))
						.propertyChange(e);
			}
		}
	}

	public void this_windowOpened(WindowEvent e) {
		fillCombos();
	}

	void fillCombos() {
		this.cmbMarca.removeAllItems();
		this.cmbDescripcion.removeAllItems();
		this.cmbColor.removeAllItems();
		this.cmbMarca.addItem("");
		this.cmbDescripcion.addItem("");
		this.cmbColor.addItem("");

		for (String marca : AppInstance.marcas2id.keySet()) {
			this.cmbMarca.addItem(marca);
		}
		for (String descripcion : AppInstance.descripciones2id.keySet()) {
			this.cmbDescripcion.addItem(descripcion);
		}
		for (String color : AppInstance.colores2id.keySet()) {
			this.cmbColor.addItem(color);
		}
	}

	public void cmbEstilo2_actionPerformed(ActionEvent e) {
		if (cmbMarca.getSelectedItem() != null) {
			cmbMarca.setSelectedItem(((String) cmbMarca.getSelectedItem())
					.toUpperCase());
		}
	}

	public void cmbEstilo3_actionPerformed(ActionEvent e) {
		if (cmbDescripcion.getSelectedItem() != null) {
			cmbDescripcion.setSelectedItem(((String) cmbDescripcion
					.getSelectedItem()).toUpperCase());
		}
	}

	public void cmbColor_actionPerformed(ActionEvent e) {
		if (cmbColor.getSelectedItem() != null) {
			cmbColor.setSelectedItem(((String) cmbColor.getSelectedItem())
					.toUpperCase());
		}
	}

}

class FrmAgregarProductoNuevo_cmbColor_actionAdapter implements ActionListener {
	private final FrmAgregarProductoNuevo adaptee;

	FrmAgregarProductoNuevo_cmbColor_actionAdapter(
			FrmAgregarProductoNuevo adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.cmbColor_actionPerformed(e);
	}
}

class FrmAgregarProductoNuevo_cmbEstilo2_actionAdapter implements
		ActionListener {
	private final FrmAgregarProductoNuevo adaptee;

	FrmAgregarProductoNuevo_cmbEstilo2_actionAdapter(
			FrmAgregarProductoNuevo adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.cmbEstilo2_actionPerformed(e);
	}
}

class FrmAgregarProductoNuevo_cmbEstilo3_actionAdapter implements
		ActionListener {
	private final FrmAgregarProductoNuevo adaptee;

	FrmAgregarProductoNuevo_cmbEstilo3_actionAdapter(
			FrmAgregarProductoNuevo adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.cmbEstilo3_actionPerformed(e);
	}
}

class FrmAgregarProductoNuevo_this_windowAdapter extends WindowAdapter {
	private final FrmAgregarProductoNuevo adaptee;

	FrmAgregarProductoNuevo_this_windowAdapter(FrmAgregarProductoNuevo adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		adaptee.this_windowOpened(e);
	}
}

class FrmAgregarProductoNuevo_cmdAgregar_mouseAdapter extends MouseAdapter {
	private final FrmAgregarProductoNuevo adaptee;

	FrmAgregarProductoNuevo_cmdAgregar_mouseAdapter(
			FrmAgregarProductoNuevo adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		adaptee.cmdAgregar_mouseClicked(e);
	}
}

class FrmAgregarProductoNuevo_cmdCancelar_mouseAdapter extends MouseAdapter {
	private final FrmAgregarProductoNuevo adaptee;

	FrmAgregarProductoNuevo_cmdCancelar_mouseAdapter(
			FrmAgregarProductoNuevo adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		adaptee.cmdCancelar_mouseClicked(e);
	}
}

class FrmAgregarProductoNuevo_txtPrecioCosto_actionAdapter implements
		ActionListener {
	private final FrmAgregarProductoNuevo adaptee;

	FrmAgregarProductoNuevo_txtPrecioCosto_actionAdapter(
			FrmAgregarProductoNuevo adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		adaptee.txtPrecioCosto_actionPerformed(e);
	}
}

class FrmAgregarProductoNuevo_cmdLimpiar_actionAdapter implements
		ActionListener {
	private final FrmAgregarProductoNuevo adaptee;

	FrmAgregarProductoNuevo_cmdLimpiar_actionAdapter(
			FrmAgregarProductoNuevo adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.cmdLimpiar_actionPerformed(e);
	}
}

class FrmAgregarProductoNuevo_cmbTipoProducto_actionAdapter implements
		ActionListener {
	private final FrmAgregarProductoNuevo adaptee;

	FrmAgregarProductoNuevo_cmbTipoProducto_actionAdapter(
			FrmAgregarProductoNuevo adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.cmbTipoProducto_actionPerformed(e);
	}
}

class FrmAgregarProductoNuevo_cmbUnidad_actionAdapter implements ActionListener {
	private final FrmAgregarProductoNuevo adaptee;

	FrmAgregarProductoNuevo_cmbUnidad_actionAdapter(
			FrmAgregarProductoNuevo adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.cmbUnidad_actionPerformed(e);
	}
}
