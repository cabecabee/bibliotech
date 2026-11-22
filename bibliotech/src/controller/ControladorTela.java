/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Dimension;
import javax.swing.JDesktopPane;
import view.TelaGerenciar;
import view.TelaPesquisar;

/**
 *
 * @author Usuario
 */
public class ControladorTela {
    JDesktopPane jDesktopPane;

    public ControladorTela(JDesktopPane jDesktopPane) {
        this.jDesktopPane = jDesktopPane;
    }
    
    public void carregarTelaGerenciar(){
        jDesktopPane.removeAll();
        jDesktopPane.updateUI();
        Dimension resolucao = jDesktopPane.getSize();
        TelaGerenciar telaGerenciar = new TelaGerenciar();
        telaGerenciar.setSize(resolucao);
        telaGerenciar.setLocation(0, 0);
        jDesktopPane.add(telaGerenciar);
        jDesktopPane.setVisible(true);
    }
    
    public void carregarTelaPesquisar(){
        jDesktopPane.removeAll();
        jDesktopPane.updateUI();
        Dimension resolucao = jDesktopPane.getSize();
        TelaPesquisar telaPesquisar = new TelaPesquisar();
        telaPesquisar.setSize(resolucao);
        telaPesquisar.setLocation(0, 0);
        jDesktopPane.add(telaPesquisar);
        jDesktopPane.setVisible(true);
    }
}
