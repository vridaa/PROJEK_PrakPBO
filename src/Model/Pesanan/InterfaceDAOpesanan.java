/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Model.Pesanan;

import java.util.List;

/**
 *
 * @author ASUS
 */
public interface InterfaceDAOpesanan {
    public void insert(ModelPesanan pesana);
    public List<ModelPesanan> getAll(int userid);

}
