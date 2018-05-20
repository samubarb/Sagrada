package it.polimi.ingsw.view;

public class VDice {
        // U+2680 to U+2685
        public static final String[] faces = {
                "\u2680",
                "\u2681",
                "\u2682",
                "\u2683",
                "\u2684",
                "\u2685"
        };

        private int value;
        private VColor color;

        public VDice(int value, VColor color) {
                this.value = value;
                this.color = color;
        }

        @Override
        public String toString() {
                return this.color. toString() + "[" + this.faces[value - 1] + "]" + this.color.RESET;
        }
}
