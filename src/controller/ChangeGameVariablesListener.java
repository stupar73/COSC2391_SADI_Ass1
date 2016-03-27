package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.GameVariablesDialog;

public class ChangeGameVariablesListener implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        GameVariablesDialog dialog = new GameVariablesDialog();
        dialog.show();
    }
}
