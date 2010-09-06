/*
 * listFix() - Fix Broken Playlists!
 * Copyright (C) 2001-2010 Jeremy Caron
 * 
 * This file is part of listFix().
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, please see http://www.gnu.org/licenses/
 */

package listfix.view.dialogs;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import listfix.model.*;
import listfix.view.support.ClosestMatchColumnListener;

public class ClosestMatchChooserDialog extends javax.swing.JDialog
{
	public static final int OK = 0;
	public static final int CANCEL = 1;
	private static final long serialVersionUID = -5374761780814261291L;
	private List<MatchedPlaylistEntry> matchData;
	private int choice = -1;
	private int resultCode = CANCEL;
	private MatchedFileTableModel tableModel = null;

	/** Creates new form ClosestMatchChooserDialog */
	public ClosestMatchChooserDialog(java.awt.Frame parent, List<MatchedPlaylistEntry> matches, boolean modal)
	{
		super(parent, modal);
		matchData = matches;
		tableModel = new MatchedFileTableModel(matchData);
		initComponents();
		initColumnSizesAndSorting(resultsTable);
		resultsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
	}

	public int getChoice()
	{
		return choice;
	}

	public int getResultCode()
	{
		return resultCode;
	}

	public void setResultCode(int i)
	{
		resultCode = i;
	}

	public void center()
	{
		Point parentLocation = this.getParent().getLocationOnScreen();

		double x = parentLocation.getX();
		double y = parentLocation.getY();

		int width = this.getParent().getWidth();
		int height = this.getParent().getHeight();

		this.setLocation((int) x + (width - this.getWidth()) / 2, (int) y + (height - this.getHeight()) / 2);
	}

	@Override
	public void setVisible(boolean visible)
	{
		choice = CANCEL;
		super.setVisible(visible);
	}

    private String getRowToolTip(MouseEvent e)
    {
        int rowIx = resultsTable.rowAtPoint(e.getPoint());
        if (rowIx >= 0)
            return ((MatchedFileTableModel)resultsTable.getModel()).vectorData.get(rowIx).getPlaylistFile().getPath();
        else
            return null;
    }

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultsTable =
        new listfix.view.support.ZebraJTable()
        {
            //Implement table cell tool tips.
            public String getToolTipText(MouseEvent e)
            {
                //return ((MatchedFileTableModel)this.getModel()).vectorData.get(rowAtPoint(e.getPoint())).getPlaylistFile().getPath();
                return getRowToolTip(e);
            }
        }

        ;
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setTitle("Closest Matches");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setPreferredSize(new java.awt.Dimension(453, 200));

        resultsTable.setModel(tableModel);
        resultsTable.setFillsViewportHeight(true);
        resultsTable.setFont(new java.awt.Font("Verdana", 0, 9));
        resultsTable.setRowHeight(20);
        resultsTable.setShowHorizontalLines(false);
        resultsTable.setShowVerticalLines(false);
        resultsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                resultsTableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(resultsTable);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jButton1.setFont(new java.awt.Font("Verdana", 0, 9));
        jButton1.setText("OK");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        jButton2.setFont(new java.awt.Font("Verdana", 0, 9));
        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);

        jPanel1.add(jPanel2, java.awt.BorderLayout.SOUTH);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 9));
        jLabel1.setText("Choose a replacement from the list below");
        jPanel3.add(jLabel1, new java.awt.GridBagConstraints());

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 9));
        jLabel2.setText("and click ok, or click cancel to quit the operation.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel3.add(jLabel2, gridBagConstraints);

        jPanel1.add(jPanel3, java.awt.BorderLayout.NORTH);

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
		setVisible(false);
		dispose();
		setResultCode(CANCEL);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
		setVisible(false);
		choice = resultsTable.getSelectedRow();
		dispose();
		setResultCode(OK);
    }//GEN-LAST:event_jButton1ActionPerformed

	/** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
		setVisible(false);
		dispose();
    }//GEN-LAST:event_closeDialog

    private void resultsTableMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_resultsTableMousePressed
    {//GEN-HEADEREND:event_resultsTableMousePressed
		if (resultsTable.getSelectedRowCount() > 0)
		{
			jButton1.setEnabled(true);
		}

		int currentlySelectedRow = resultsTable.getSelectedRow();
		if (currentlySelectedRow != -1 && evt.getClickCount() == 2)
		{
			setVisible(false);
			choice = currentlySelectedRow;
			dispose();
			setResultCode(OK);
		}
    }//GEN-LAST:event_resultsTableMousePressed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[])
	{
		new ClosestMatchChooserDialog(new javax.swing.JFrame(), null, true).setVisible(true);
	}

	private void initColumnSizesAndSorting(JTable table)
	{
		MatchedFileTableModel model = (MatchedFileTableModel) table.getModel();
		Component comp = null;
		int headerWidth = 0;
		int cellWidth = 0;
		Object[] longValues = model.longestValues();
		Enumeration<TableColumn> columns = table.getColumnModel().getColumns();

		JTableHeader header = table.getTableHeader();
		header.setUpdateTableInRealTime(false);
		header.addMouseListener(new ClosestMatchColumnListener(resultsTable, model));

		TableCellRenderer headerRenderer = header.getDefaultRenderer();
		while (columns.hasMoreElements())
		{
			TableColumn column = columns.nextElement();
			comp = headerRenderer.getTableCellRendererComponent(table, column.getHeaderValue(), false, false, 0, 0);
			headerWidth = comp.getPreferredSize().width;
			if (((String) column.getHeaderValue()).toLowerCase().contains("score"))
			{
				comp = table.getDefaultRenderer(model.getColumnClass(1)).getTableCellRendererComponent(table, longValues[1], false, false, 0, 1);
			}
			else
			{
				comp = table.getDefaultRenderer(model.getColumnClass(0)).getTableCellRendererComponent(table, longValues[0], false, false, 0, 0);
			}
			cellWidth = comp.getPreferredSize().width;
			if (((String) column.getHeaderValue()).toLowerCase().contains("score"))
			{
				column.setPreferredWidth(Math.max(headerWidth, cellWidth) + 40);
			}
			else
			{
				column.setPreferredWidth(Math.max(headerWidth, cellWidth));
			}
		}
	}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private listfix.view.support.ZebraJTable resultsTable;
    // End of variables declaration//GEN-END:variables
}
