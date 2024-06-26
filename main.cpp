#include <iostream>
using namespace std;

bool gameOver;

void Setup() {
    gameOver = false;
}

void Draw() {
    
}

void Input() {
    
}

void Logic() {
    
}

int main() {
    Setup();
    while (!gameOver) {
        Draw();
        Input();
        Logic();
    }
    cout << "Hello" << endl;
    return 0;
}