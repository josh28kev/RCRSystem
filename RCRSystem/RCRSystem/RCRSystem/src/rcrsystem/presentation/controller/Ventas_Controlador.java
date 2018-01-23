package rcrsystem.presentation.controller;

import Modelo.Bulto;
import Modelo.Cliente;
import Modelo.Inventario;
import Modelo.Conductor;
import Modelo.Lista_Empaque;
import Modelo.Venta;
import Modelo.dao.Bulto_DAO;
import Modelo.dao.Inventario_DAO;
import java.awt.Toolkit;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import rcrsystem.presentation.model.Ventas_Modelo;
import rcrsystem.presentation.view.VentanaCarga;
import rcrsystem.presentation.view.VentanaVenta;

public class Ventas_Controlador {

    public Ventas_Controlador(Ventas_Modelo modelo, VentanaVenta vista) {
        this.a_vista = vista;
        this.a_modelo = modelo;
        vista.setControlador(this);
        vista.setModelo(modelo);
        this.cargar();
    }

    public void cargar() {
        a_vista.cliente_jComboBox.removeAllItems();
        List<Cliente> clientes = new ArrayList<Cliente>();
        clientes = a_modelo.obtener_clientes();
        for (int i = 0; i < clientes.size(); i++) {
            a_vista.cliente_jComboBox.addItem("(" + clientes.get(i).obtener_codigo_c() + ")    " + clientes.get(i).obtener_nombre_c());
        }

    }

    public Lista_Empaque temporal_actual() {
        Lista_Empaque venta = new Lista_Empaque();
        Conductor conductor = new Conductor();

        if (a_vista.maritimo_jRadioButton.isSelected()) {
            venta.poner_medio_transporte(1);
        } else {
            venta.poner_medio_transporte(2);
        }

        venta.poner_cliente(this.obtener_cliente());

        venta.poner_destino(a_vista.destino_jTextField.getText());
        venta.poner_marchamo(a_vista.txtMarchamo.getText());

        if (a_vista.pesoNeto_jTextField.getText().length() != 0) {
            BigDecimal peso = new BigDecimal(a_vista.pesoNeto_jTextField.getText());
            venta.poner_peso_neto(peso);
        } else {
            a_vista.pesoNeto_jTextField.setText("");
        }

        venta.poner_peso_bruto(new BigDecimal(a_vista.pesoBruto_jLabel.getText()));

        if (a_vista.idtrans_jTextField.getText().length() != 0) {

            conductor.poner_id(a_vista.idtrans_jTextField.getText());
        }
        if (a_vista.nacionTrans_jTextField.getText().length() != 0) {

            conductor.poner_nacionalidad(a_vista.nacionTrans_jTextField.getText());
        }
        if (a_vista.nombreTrans_jTextField.getText().length() != 0) {
            conductor.poner_nombre(a_vista.nombreTrans_jTextField.getText());
        }
        venta.poner_conductor(conductor);

        if (a_vista.placa_jTextField.getText().length() != 0) {
            venta.poner_placa(a_vista.placa_jTextField.getText());
        }
        if (a_vista.marca_jTextField.getText().length() != 0) {
            venta.poner_marca(a_vista.marca_jTextField.getText());
        }

        if (a_vista.furgon_jTextField.getText().length() != 0) {
            venta.poner_furgon(a_vista.furgon_jTextField.getText());
        }

        if (a_vista.txtTransportista.getText().length() != 0) {
            venta.poner_transportista(a_vista.txtTransportista.getText());
        }

        if (a_vista.txtCodigoTransportista.getText().length() != 0) {
            venta.poner_codigoTransportista(a_vista.txtCodigoTransportista.getText());
        }

        if (a_vista.chasis_jTextField.getText().length() != 0) {
            venta.poner_chasis(a_vista.chasis_jTextField.getText());
        }

        if (a_vista.txtNumContenedor.getText().length() != 0) {
            venta.poner_numero_contenedor(a_vista.txtNumContenedor.getText());
        }
        return venta;
    }

    public int validar_lista_empaque() {
        Lista_Empaque venta = new Lista_Empaque();
        try {
            Conductor conductor = new Conductor();
            a_modelo.eliminar_errores();
            String codigo = a_modelo.obtener_secuencia_codListEmpaque();
            venta.poner_codigo_l(Integer.parseInt(codigo));
            if (a_vista.maritimo_jRadioButton.isSelected()) {
                venta.poner_medio_transporte(1);
            } else {
                venta.poner_medio_transporte(2);
            }

            venta.poner_cliente(this.obtener_cliente());
            venta.poner_total_bultos(a_total_Bultos);
            if (a_vista.destino_jTextField.getText().length() == 0) {
                a_modelo.obtener_errores().put("destino", "Debe llenar el campo");
            } else {
                venta.poner_destino(a_vista.destino_jTextField.getText());
            }

            if (a_vista.txtMarchamo.getText().length() == 0) {
                a_modelo.obtener_errores().put("marchamo", "Debe llenar el campo");
            } else {
                venta.poner_marchamo(a_vista.txtMarchamo.getText());
            }

            if (a_vista.pesoNeto_jTextField.getText().length() == 0) {
                a_modelo.obtener_errores().put("peso", "Debe llenar el campo");
            } else {
                BigDecimal peso = new BigDecimal(a_vista.pesoNeto_jTextField.getText());
                if (peso.compareTo(new BigDecimal("0.0")) == -1) {
                    a_modelo.obtener_errores().put("peso", "Cantidad incorrecta");
                } else {
                    venta.poner_peso_neto(new BigDecimal(a_vista.pesoNeto_jTextField.getText()));
                }
            }

            venta.poner_peso_bruto(new BigDecimal(a_vista.pesoBruto_jLabel.getText()));
            if (venta.obtener_medio_transporte() == 2) {//terrestre
                if (a_vista.marca_jTextField.getText().length() == 0) {
                    a_modelo.obtener_errores().put("marca", "Debe llenar este campo");
                } else {
                    venta.poner_marca(a_vista.marca_jTextField.getText());
                }

                if (a_vista.chasis_jTextField.getText().length() == 0) {
                    a_modelo.obtener_errores().put("chasis", "Debe llenar este campo");
                } else {
                    venta.poner_chasis(a_vista.chasis_jTextField.getText());
                }
                if (a_vista.txtCodigoTransportista.getText().length() == 0) {
                    a_modelo.obtener_errores().put("codTransportista", "Debe llenar este campo");
                } else {
                    venta.poner_codigoTransportista(a_vista.txtCodigoTransportista.getText());
                }
            } else if (venta.obtener_medio_transporte() == 1) { //maritimo
                if (a_vista.txtNumContenedor.getText().length() == 0) {
                    a_modelo.obtener_errores().put("numContenedor", "Debe llenar este campo");
                } else {
                    venta.poner_numero_contenedor(a_vista.txtNumContenedor.getText());
                }
            }

            if (a_vista.idtrans_jTextField.getText().length() == 0) {
                a_modelo.obtener_errores().put("idT", "Debe llenar el campo");
            } else {
                conductor.poner_id(a_vista.idtrans_jTextField.getText());
            }
            if (a_vista.nacionTrans_jTextField.getText().length() == 0) {
                a_modelo.obtener_errores().put("nacionalidadT", "Debe llenar este campo");
            } else {
                conductor.poner_nacionalidad(a_vista.nacionTrans_jTextField.getText());
            }
            if ((1900 + a_vista.campoFecha.getDate().getYear()) > ((1900 + new Date().getYear()) - 17)) {
                a_modelo.obtener_errores().put("añoT", "Chofer menor de 18 años");
            } else {
                String Fecha1 = (1900 + a_vista.campoFecha.getDate().getYear()) + "-" + (a_vista.campoFecha.getDate().getMonth() + 1) + "-" + a_vista.campoFecha.getDate().getDate();
                conductor.poner_fecha_nacimiento(Fecha1);
            }
            if (a_vista.nombreTrans_jTextField.getText().length() == 0) {
                a_modelo.obtener_errores().put("nombreT", "Debe llenar este campo");
            } else {
                conductor.poner_nombre(a_vista.nombreTrans_jTextField.getText());
            }
            venta.poner_conductor(conductor);

            if (a_vista.placa_jTextField.getText().length() == 0) {
                a_modelo.obtener_errores().put("placa", "Debe llenar el campo");
            } else {
                venta.poner_placa(a_vista.placa_jTextField.getText());
            }

            if (a_vista.furgon_jTextField.getText().length() == 0) {
                a_modelo.obtener_errores().put("furgon", "Debe llenar este campo");
            } else {
                venta.poner_furgon(a_vista.furgon_jTextField.getText());
            }
            if (a_vista.txtTransportista.getText().length() == 0) {
                a_modelo.obtener_errores().put("transportista", "Debe llenar este campo");
            } else {
                venta.poner_transportista(a_vista.txtTransportista.getText());
            }
        } catch (Exception e) {
            System.out.print(e);
        }
        if (!a_modelo.obtener_errores().isEmpty() || a_modelo.obtener_venta().isEmpty()) {
            a_modelo.colocar_actual(venta);
            if (!a_modelo.obtener_errores().isEmpty()) {
                return 1;
            } else {
                return 2;
            }
        } else {
            a_modelo.colocar_actual(venta);
            return 0;
        }
    }

    public void reportar_errores() {
        a_modelo.colocar_mensaje("¡Hay Errores!");
        a_modelo.colocar_actual(a_modelo.obtener_actual());
    }

    public void reportar_error_venta_nula() {
        a_modelo.colocar_mensaje("¡No ha ingresado bultos a la lista de empaque!");
        a_modelo.colocar_actual(a_modelo.obtener_actual());
    }

    public void generar_lista_empaque() {
        Lista_Empaque venta = new Lista_Empaque();
        try {

            Conductor conductor = new Conductor();
            a_modelo.eliminar_errores();
            String codigo = a_modelo.obtener_secuencia_codListEmpaque();
            venta.poner_codigo_l(Integer.parseInt(codigo));
            if (a_vista.maritimo_jRadioButton.isSelected()) {
                venta.poner_medio_transporte(1);
            } else {
                venta.poner_medio_transporte(2);
            }

            venta.poner_cliente(this.obtener_cliente());

            if (a_vista.destino_jTextField.getText().length() == 0) {
                a_modelo.obtener_errores().put("destino", "Debe llenar el campo");
            } else {
                venta.poner_destino(a_vista.destino_jTextField.getText());
            }
            if (a_vista.txtMarchamo.getText().length() == 0) {
                a_modelo.obtener_errores().put("marchamo", "Debe llenar el campo");
            } else {
                venta.poner_marchamo(a_vista.txtMarchamo.getText());
            }
            if (a_vista.pesoNeto_jTextField.getText().length() == 0) {
                a_modelo.obtener_errores().put("peso", "Debe llenar el campo");
            } else {
                BigDecimal peso = new BigDecimal(a_vista.pesoNeto_jTextField.getText());
                if (peso.compareTo(new BigDecimal("0.0")) == -1) {
                    a_modelo.obtener_errores().put("peso", "Cantidad incorrecta");
                } else {
                    venta.poner_peso_neto(new BigDecimal(a_vista.pesoNeto_jTextField.getText()));
                }
            }
            venta.poner_peso_bruto(new BigDecimal(a_vista.pesoBruto_jLabel.getText()));

            if (a_vista.idtrans_jTextField.getText().length() == 0) {
                a_modelo.obtener_errores().put("idT", "Debe llenar el campo");
            } else {
                int id = Integer.parseInt(a_vista.idtrans_jTextField.getText());
                if (id < 0 || a_vista.idtrans_jTextField.getText().length() < 9 || a_vista.idtrans_jTextField.getText().length() > 9) {
                    a_modelo.obtener_errores().put("idT", "ID incorrecta");
                } else {
                    conductor.poner_id(a_vista.idtrans_jTextField.getText());
                }
            }
            if (a_vista.nacionTrans_jTextField.getText().length() == 0) {
                a_modelo.obtener_errores().put("nacionalidadT", "Debe llenar este campo");
            } else {
                conductor.poner_nacionalidad(a_vista.nacionTrans_jTextField.getText());
            }
            if ((1900 + a_vista.campoFecha.getDate().getYear()) > 2002) {
                a_modelo.obtener_errores().put("añoT", "El año es muy bajo para ser chofer");
            } else {
                String Fecha1 = (1900 + a_vista.campoFecha.getDate().getYear()) + "-" + (a_vista.campoFecha.getDate().getMonth() + 1) + "-" + a_vista.campoFecha.getDate().getDate();
                conductor.poner_fecha_nacimiento(Fecha1);
            }
            if (a_vista.nombreTrans_jTextField.getText().length() == 0) {
                a_modelo.obtener_errores().put("nombreT", "Debe llenar este campo");
            } else {
                conductor.poner_nombre(a_vista.nombreTrans_jTextField.getText());
            }

            venta.poner_conductor(conductor);
            if (a_vista.placa_jTextField.getText().length() == 0) {
                a_modelo.obtener_errores().put("placa", "Debe llenar el campo");
            } else {
                int placa = Integer.parseInt(a_vista.placa_jTextField.getText());
                if (placa < 0) {
                    a_modelo.obtener_errores().put("idT", "Placa incorrecta");
                } else {
                    venta.poner_placa(a_vista.placa_jTextField.getText());
                }
            }
            if (a_vista.marca_jTextField.getText().length() == 0) {
                a_modelo.obtener_errores().put("marca", "Debe llenar este campo");
            } else {
                venta.poner_marca(a_vista.marca_jTextField.getText());
            }
            if (a_vista.furgon_jTextField.getText().length() == 0) {
                a_modelo.obtener_errores().put("furgon", "Debe llenar este campo");
            } else {
                venta.poner_furgon(a_vista.furgon_jTextField.getText());
            }
            if (a_vista.txtTransportista.getText().length() == 0) {
                a_modelo.obtener_errores().put("transportista", "Debe llenar este campo");
            } else {
                venta.poner_transportista(a_vista.txtTransportista.getText());
            }
            if (a_vista.txtCodigoTransportista.getText().length() == 0) {
                a_modelo.obtener_errores().put("codTransportista", "Debe llenar este campo");
            } else {
                venta.poner_codigoTransportista(a_vista.txtCodigoTransportista.getText());
            }

            if (a_vista.txtNumContenedor.getText().length() == 0) {
                a_modelo.obtener_errores().put("numContenedor", "Debe llenar este campo");
            } else {
                venta.poner_numero_contenedor(a_vista.txtNumContenedor.getText());
            }
            if (a_vista.chasis_jTextField.getText().length() == 0) {
                a_modelo.obtener_errores().put("chasis", "Debe llenar este campo");
            } else {
                venta.poner_chasis(a_vista.chasis_jTextField.getText());
            }
            if (!a_modelo.obtener_errores().isEmpty() || a_modelo.obtener_venta().isEmpty()) {
                a_modelo.colocar_mensaje("¡Hay Errores!");
                a_modelo.colocar_actual(venta);
            } else {
                int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que desea ingresar la lista de empaque?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (opcion == JOptionPane.YES_OPTION) {
                    a_modelo.guardar_lista_empaque(venta);
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "¡Lista de empaque registrada correctamente!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    this.limpiar_datos();
                    this.limpiar_bulto();
                    opcion = JOptionPane.showConfirmDialog(null, "¿Desea seguir registrando listas de empaque?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (opcion == JOptionPane.YES_OPTION) {
                        this.limpiar_datos();
                        this.limpiar_bulto();
                    } else {
                        a_vista.setVisible(false);
                    }
                }
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public void guardar_lista_empaque() {
        try {
            a_modelo.guardar_lista_empaque(a_modelo.obtener_actual());
        } catch (Exception e) {

        }
    }

    public void procesar_lista_empaque(VentanaCarga ventana, int tV) {
        this.a_vista.setVisible(false);
        this.guardar_lista_empaque();
        ventana.setVisible(false);
        Toolkit.getDefaultToolkit().beep();
        this.a_vista.setVisible(true);
        JOptionPane.showMessageDialog(null, "¡Lista de empaque registrada correctamente!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        this.limpiar_datos();
        this.limpiar_bulto();
        int opcion = JOptionPane.showConfirmDialog(null, "¿Desea seguir registrando listas de empaque?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcion == JOptionPane.YES_OPTION) {
            this.limpiar_datos();
            this.limpiar_bulto();
        } else {
            eliminar_errores();
            limpiar_bulto();
            limpiar_datos();
            a_vista.setVisible(false);
            Progreso_Menu_Prin_Controlador v = new Progreso_Menu_Prin_Controlador(new VentanaCarga(), tV);
            v.execute();
        }
    }

    public void limpiar_datos() {
        a_modelo.colocar_venta(new ArrayList());
        List<Venta> rows = new ArrayList<Venta>();
        a_modelo.colocar_materiales_venta(rows, 0);
        a_modelo.colocar_actual(new Lista_Empaque());
        a_vista.numero_jlabel.setText("1");

    }

    public void eliminar_errores() {
        a_modelo.colocar_errores(new Hashtable());
    }

    public String obtener_fecha() {
        Calendar c1 = new GregorianCalendar();
        String dia = "";
        String mes = "";
        String año = "";

        if (c1.get(Calendar.DATE) < 10) {
            dia = "0" + String.valueOf(c1.get(Calendar.DATE));
        } else {
            dia = String.valueOf(c1.get(Calendar.DATE));
        }
        if ((c1.get(Calendar.MONTH) + 1) < 10) {
            mes = "0" + String.valueOf(c1.get(Calendar.MONTH) + 1);
        } else {
            mes = String.valueOf(c1.get(Calendar.MONTH) + 1);
        }
        año = String.valueOf(c1.get(Calendar.YEAR));
        return dia + "-" + mes + "-" + año;
    }

    public void cargar_bulto(VentanaCarga carga) {
        a_vista.setEnabled(false);
        Bulto bulto = new Bulto();
        String material = "";
        String codigo = "";
        String codigo_aux = "";
        Inventario inv = new Inventario();
        if (a_vista.codigo_bultojTextField.getText().length() != 0) {
            codigo = a_vista.codigo_bultojTextField.getText();
            if (codigo.substring(0, 1).equals("b")) {
                codigo_aux = codigo;
                codigo = "B" + codigo_aux.substring(1);
            }
            bulto = Bulto_DAO.obtener_bulto(codigo);
            if (bulto != null) {
                material = bulto.obtener_material().obtener_codigo();
                String aux = material.substring(1);
                inv = Inventario_DAO.obtener_inventario(aux);
                if (inv.obtener_cantidad().compareTo(new BigDecimal(19999)) == 1 && bulto.obtener_estado() == 1) {
                    this.a_vista.material_bultojTextField.setText(bulto.obtener_material().obtener_t_material().obtener_nombre());
                    if (bulto.obtener_tipo() == 1) {
                        a_vista.tipo_boton.setText("Paca");
                    } else {
                        a_vista.tipo_boton.setText("Saca");
                    }
                    a_vista.pesojTextField1.setText(String.valueOf(bulto.obtener_peso()));
                    a_modelo.colocar_bulto_actual(bulto);
                    a_vista.agregarbtn.setEnabled(true);
                    a_vista.jButton1.setEnabled(true);
                    a_vista.setEnabled(true);
                    a_vista.toFront();
                } else {
                    a_vista.jButton1.setEnabled(true);
                    Toolkit.getDefaultToolkit().beep();
                    carga.setVisible(false);
                    a_vista.setEnabled(true);
                    a_vista.toFront();
                    if (inv.obtener_cantidad().compareTo(new BigDecimal(21000)) == -1) {
                        a_vista.toFront();
                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(null, "¡La cantidad en inventario del material " + inv.obtener_material().obtener_nombre() + " es menor a 21 toneladas!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        a_vista.toFront();
                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(null, "¡El bulto no está disponible para la venta!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    }
                    this.limpiar_bulto();
                }
            } else {
                a_vista.toFront();
                a_vista.jButton1.setEnabled(true);
                carga.setVisible(false);
                a_vista.setEnabled(true);
                a_vista.toFront();
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "¡El bulto no existe!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                this.limpiar_bulto();
            }
        } else {
            a_vista.toFront();
            a_vista.jButton1.setEnabled(true);
            carga.setVisible(false);
            a_vista.setEnabled(true);
            a_vista.toFront();
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "¡Debe digitar el código del bulto!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            this.limpiar_bulto();
        }
    }

    boolean existe_bulto(String codigo) {
        for (Venta v : a_modelo.obtener_venta()) {
            if (v.obtener_bulto().obtener_codigo().equals(codigo)) {
                return true;
            }
        }
        return false;
    }

    public void limpiar_bulto() {
        a_vista.material_bultojTextField.setText("");
        a_vista.tipo_boton.setText("");
        a_modelo.colocar_bulto_actual(new Bulto());
        a_vista.pesojTextField1.setText("");
        a_vista.agregarbtn.setEnabled(false);
        a_vista.codigo_bultojTextField.setText("");
    }

    public void agregar_bulto() {
        Venta v = new Venta();
        Bulto bulto = a_modelo.obtener_bulto_actual();
        Lista_Empaque le = this.temporal_actual();
        if (bulto != null) {
            if (!this.existe_bulto(bulto.obtener_codigo())) {
                if (a_vista.pesojTextField1.getText().length() != 0) {
                    if (new BigDecimal(a_vista.pesojTextField1.getText()).compareTo(new BigDecimal("10")) == 1) {
                        v.poner_bulto(bulto);
                        v.poner_numero(Integer.parseInt(a_vista.numero_jlabel.getText()));
                        a_modelo.obtener_venta().add(v);
                        this.limpiar_bulto();
                        a_total_Bultos = a_total_Bultos + 1;
                        this.a_vista.lbCantBultos1.setText(String.valueOf(a_total_Bultos));
                        a_modelo.colocar_actual(le);
                        a_modelo.colocar_materiales_venta(a_modelo.obtener_venta(), 0);
                        a_vista.numero_jlabel.setText(String.valueOf(v.obtener_numero() + 1));
                    } else {
                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(null, "¡El peso del bulto es incorrecto!", "Aviso", JOptionPane.INFORMATION_MESSAGE);

                    }
                } else {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "¡El peso del bulto está vacío!", "Aviso", JOptionPane.INFORMATION_MESSAGE);

                }
            } else {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "¡Bulto duplicado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                this.limpiar_bulto();
            }
        } else {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "¡No seleccionó ningún bulto!", "Aviso", JOptionPane.INFORMATION_MESSAGE);

        }
    }

    public void borrar_bulto(int row) {
        try {
            if (row > -1) {
                int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que desea borrar el bulto?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (opcion == JOptionPane.YES_OPTION) {
                    Venta v = a_modelo.obtener_modelo_material().obtener_fila_a(row);
                    a_modelo.eliminar_venta(v);
                    a_total_Bultos = a_total_Bultos - 1;
                    this.a_vista.lbCantBultos1.setText(String.valueOf(a_total_Bultos));
                    if (a_modelo.obtener_venta().size() > 0) {
                        a_vista.numero_jlabel.setText(String.valueOf(a_modelo.obtener_venta().get(a_modelo.obtener_venta().size() - 1).obtener_numero() + 1));
                    } else {
                        a_vista.numero_jlabel.setText("1");
                    }
                }
                a_vista.eliminar_jButton.setEnabled(true);
            } else {
                Venta v = a_modelo.obtener_modelo_material().obtener_fila_a(row);
            }
        } catch (Exception ex) {
            a_modelo.colocar_mensaje("¡No se ha seleccionado ningún bulto!");
            a_vista.eliminar_jButton.setEnabled(true);
            a_modelo.colocar_materiales_venta(a_modelo.obtener_venta(), 0);
        }
        a_modelo.colocar_materiales_venta(a_modelo.obtener_venta(), 0);
    }

    public Cliente obtener_cliente() {
        Cliente cliente = new Cliente();
        StringTokenizer st = new StringTokenizer(a_vista.cliente_jComboBox.getSelectedItem().toString(), "()");
        String id = "";
        String nombre = "";
        while (st.hasMoreTokens()) {
            id = st.nextToken();
            nombre = st.nextToken();
        }
        cliente.poner_codigo_c(id);
        cliente.poner_nombre_c(nombre);
        return cliente;
    }

    public BigDecimal obtener_peso_bruto() {
        return a_modelo.calcular_peso_bruto();
    }

    public void definir_cliente(Cliente c) {
        a_vista.cliente_jComboBox.setSelectedItem("(" + c.obtener_codigo_c() + ")    " + c.obtener_nombre_c());
    }

    private Ventas_Modelo a_modelo = new Ventas_Modelo();
    private VentanaVenta a_vista = new VentanaVenta();
    private int a_total_Bultos = 0;
} // Fin de la clase Ventas_Controlador
