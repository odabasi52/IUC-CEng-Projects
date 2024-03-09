#include <fstream>
#include <iostream>

using namespace std;

void get_input_create_file(){
    string input, file_name;
    cout << "Enter a file name:";
    cin >> file_name;

    cout << "Enter the content of the file:";
    getline(cin >> ws, input);

    ofstream file(file_name);
    if (file.is_open()){
        file << input << endl;
    }
    cout << input;
}

int count_words(string file_name){
    int count = 0;

    ifstream file(file_name);
    if (file.is_open()){
        string word;

        //read until encounter the whitespace
        while (file >> word){
            count++;
        }
    }

    return count;
}

int main(){
    get_input_create_file();
}