package model.state;

interface State {
    State mouseLeftClick();
    State mouseRightClick();
    State mouseMove();
    State mouseLeftDrag();
    State mouseRightDrag();
    State mouseLeftRelease();
    State mouseRightRelease();
    int getStateCode();
}
