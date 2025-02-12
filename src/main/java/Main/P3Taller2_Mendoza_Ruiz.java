package Main;

import Controller.Controller_Mendoza_Ruiz;
import Model.Model_Mendoza_Ruiz;
import View.View_Mendoza_Ruiz;

public class P3Taller2_Mendoza_Ruiz {
    public static void main(String[] args) {
        Model_Mendoza_Ruiz model = new Model_Mendoza_Ruiz("","","","","",null);
        View_Mendoza_Ruiz view = new View_Mendoza_Ruiz();
        Controller_Mendoza_Ruiz controller = new Controller_Mendoza_Ruiz(model, view);
        controller.iniciarView();
    }
}
