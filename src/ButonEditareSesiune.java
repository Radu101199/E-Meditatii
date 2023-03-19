import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

@SuppressWarnings("serial")
public class ButonEditareSesiune extends AbstractCellEditor implements TableCellEditor{
    private JButton buton;
    private JTable tabel;
    private int rand;
    private ArrayList<SesiuneMeditatii> sesiuniConfirmate;
    private DefaultTableModel model;
    private ControllerButoane cb;

    public ButonEditareSesiune(JButton buton, ArrayList<SesiuneMeditatii> sesiuniConfirmate,DefaultTableModel model ){
        this.buton = buton;
        this.sesiuniConfirmate = sesiuniConfirmate;
        this.model=model;
        cb = new ControllerButoane();
        buton.addActionListener(cb);
    }

    @Override
    public Object getCellEditorValue() {
        return buton;
    }

    @Override
    public Component getTableCellEditorComponent(JTable tabel, Object val, boolean isSelected, int rand, int coloana) {
        this.tabel = tabel;
        this.rand = rand;
        return buton;
    }
    private class ControllerButoane implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			EditareSesiuneDialog edit;
			try {
				
				edit = new EditareSesiuneDialog(sesiuniConfirmate.get(rand),tabel,model);
				model.removeRow(rand);
				sesiuniConfirmate.remove(sesiuniConfirmate.get(rand));
				model.fireTableDataChanged();
				tabel.repaint();
				
				edit.setModal(true);
				edit.setVisible(true);
				} catch (ParseException e1) {
				e1.printStackTrace();
				} 
		}
    }
}
