package App;

import App.DTO.ConnectorMySql;
import App.Model.CarDetails;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Panel extends JFrame {
    private JPanel CarPanel;
    private JTextField numberVIN;
    private JTextField brand;
    private JTextField age;
    private JTextField model;
    private JButton add;
    private JButton delate;
    private JTable CarList;
    private JScrollPane scrollPanel;
    private JTextField id;
    private JButton update;

    ConnectorMySql conn = new ConnectorMySql();

    public Panel() {
        add(CarPanel);
        setTitle("Car Hire");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conn.AddCar(brand.getText(), model.getText(), Integer.valueOf(numberVIN.getText()), Integer.valueOf(age.getText()));
                ShowCar();
            }
        });
        delate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conn.DeleteCar(Integer.valueOf(id.getText()));
                ShowCar();
            }
        });
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conn.UpdateCar(Integer.valueOf(id.getText()),brand.getText(),model.getText(),Integer.valueOf(numberVIN.getText()), Integer.valueOf(age.getText()));
                ShowCar();
            }
        });
    }

    public void ShowCar() {
        ArrayList<CarDetails> list = conn.GetCarList();

        DefaultTableModel model = new DefaultTableModel();
        String [] colNames = {"id", "marka","model", "vin", "wiek"};
        model.setColumnIdentifiers(colNames);

        CarList.setModel(model);

        Object[] row = new Object[5];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).GetId();
            row[1] = list.get(i).GetMark();
            row[2] = list.get(i).GetBrand();
            row[3] = list.get(i).GetVin();
            row[4] = list.get(i).GetAge();

            model.addRow(row);
        }
    }

}
