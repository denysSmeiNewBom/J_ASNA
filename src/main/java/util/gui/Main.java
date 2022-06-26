package util.gui;

import util.gui.DTO.TableDTO;

public class Main {
    public static void main(String[] args) {
        TableDTO tableDTO = new TableDTO();
        new MyFrame(tableDTO);
    }
}
