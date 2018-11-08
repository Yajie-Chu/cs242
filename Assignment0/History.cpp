// COMSC-165 TERM PROJECT
// Programmer: Aldo Sanjoto
// Editor(s) used: Code::Blocks
// Compiler(s) used: Code::Blocks

#include <iostream>
using std::cout;
using std::endl;
using std::cin;
using std::ios;

#include <iomanip>
using std::setw;

#include <cstdlib>

#include <cstring>

#include <fstream>
using std::ofstream;
using std::ifstream;
using std::fstream;

// Struct definition
struct course
{
  char cs[12];
  char term[7];
  double units;
  char grade;
  course* next;
};

void outputMe();
void table(const course*);
void printlist(course*&);
int courseCmp(const course*, const course*);
int myStricmp(const char*, const char*);
int myStrnicmp(const char*, const char*, int);
void restoreList(course*&, course*&);
void saveList(course*&);
void sortByUnit(course*&);
void sortByGrade(course*&);
void sortByDescription(course*&);
void sortByTerm(course*&);

int main()
{
  // Local Variables
  char buf[100];
  int choice, choice2;

  // Call identifying output statements
  outputMe();

  // New list
  course* start = 0, *end = 0;

  // Ask user whether they want to restore list or not
  while(true)
  {
    cout << endl;
    cout << "Do you want to recall the course history from the file ? ";
    cin >> buf;
    choice = atoi(buf);
    cin.ignore(1000, 10);

    if(buf[0] == 'y' || buf[0] == 'Y'|| buf[0] == 'n' || buf[0] == 'N')break;
    else
    {
      cout << "Please enter upper or lower case of Y or N only." << endl;
      continue;
    }
  }

  // If Y...Restore list
  if(buf[0] == 'y' || buf[0] == 'Y')
  {
    restoreList(start, end);
    cout << "List restored!" << endl << endl;
  }

  // While main loop
  while(true)
  {
    // Main menu
    cout << "Please choose what you want to do with your course list." << endl;
    cout << "A. Add a course " << endl;
    cout << "B. Delete a course" << endl;
    cout << "C. List the course " << endl;
    cout << "D. Sort the course" << endl;
    cout << "E. Quit program " << endl;
    cout << "What is your choice ? Enter A-E: ";
    cin >> buf;
    choice = atoi(buf);
    cout << endl;

    // If choice is a
    if(buf[0] == 'a' || buf[0] == 'A')
    {
      // Code to input a list
      course* t = new course;

      // Prompt user to enter information (course, term, units, grades)
      cout << "Please enter the course (ex. COMSC165): ";
      cin >> t->cs;

      cout << "Please enter the term (ex. FA2011): ";
      cin >> t->term;

      cout << "Please enter the units: ";
      cin >> buf;
      t->units = atof(buf);

      cout << "Please enter the grade: ";
      cin >> t->grade;
      cout << endl;

      // All purpose insertion code with end pointer
      t->next = 0;
      if (end)
        end->next = t;
      else
        start = t;
      end = t; // remember to move the end pointer!
    }

    // if choice is b
    else if(buf[0] == 'b' || buf[0] == 'B')
    {
     course* prev, *p;

     for (p = start, prev = 0; p; prev = p, p = p->next)
     {
       table(start);
       cout << setw(10) << p->cs << setw(10) << p->term << setw(10) << p->units << setw(10) << p->grade << endl << endl;
       cout << "Do you want to Keep, Delete, or Quit ? Enter (K,D,Q): ";
       cin >> buf;
       choice2 = atoi(buf);
       cout << endl;

        if(buf[0] == 'D' || buf[0] == 'd')
        {
          if(prev)
            prev->next = p->next;
          else
            start = p->next;
          delete p;
          break;
        }

        if(buf[0] == 'q' || buf[0] == 'Q')break;

        if(buf[0] == 'k' || buf[0] == 'K')continue;
      }
    }

    // if choice is c
    else if(buf[0] == 'c' || buf[0] == 'C')
    {
      table(start); // cout table before input and after input
      printlist(start); // print list call
      cout << endl;
    }

    // if choice is d
    else if(buf[0] == 'd' || buf[0] == 'D')
    {
      // while loop to ask which sorting
      while(true)
      {
        cout << "How do you want the list to be sorted? :" << endl;
        cout << "1. By term (Enter T)" << endl;
        cout << "2. By units (Enter U)" << endl;
        cout << "3. By course title (Enter C)" << endl;
        cout << "4. By grade (Enter G)" << endl;
        cout << "Your choice is T/U/C/G: ";
        cin >> buf;
        choice2 = atoi(buf);
        cin.ignore(1000, 10);

        if(buf[0] == 't' || buf[0] == 'T')
        {
          // Output term sorting list
          cout << endl;
          cout << "This list is sort by term." << endl;
          table(start);
          sortByTerm(start);
          printlist(start);
          cout << endl;
          break;
        }

        else if(buf[0] == 'U' || buf[0] == 'u')
        {
          // Output unit sorting list
          cout << endl;
          cout << "This list is sort by unit." << endl;
          table(start);
          sortByUnit(start);
          printlist(start);
          cout << endl;
          break;
        }

        else if(buf[0] == 'C' || buf[0] == 'c')
        {
          // Output course alphabetical description sorting list
          cout << endl;
          cout << "This list is sort by course alphabetical description." << endl;
          table(start);
          sortByDescription(start);
          printlist(start);
          cout << endl;
          break;
        }

        else if(buf[0] == 'g' || buf[0] == 'G')
        {
          // Output grade sorting list
          cout << endl;
          cout << "This list is sort by grade." << endl;
          table(start);
          sortByGrade(start);
          printlist(start);
          cout << endl;
          break;
        }

        else
        {
          cout << "Please enter only lower or upper T, U, G, C. Try again!" << endl << endl;
          continue;
        }
      }
    }

    // If user choose to E, then break out from loop
    else if (buf[0] == 'e' || buf[0] == 'E')break;

    // Validation else statement
    else
    {
      cout << "Please enter A-B upper or lower case. Try again!" << endl << endl;
      continue;
    }
  }

  saveList(start);
  cout << "Thanks for using my program !" << endl;

  // Code of deallocate memory
  while (start) // zero when the list is empty
  {
    course* next = start->next; // save start's next before killing off start
    delete start; // kill off start
    start = next; // a new start
  }
}

void outputMe()
{
  // Identifying output statements
  cout << "COMSC-165 TERM PROJECT" << endl;
  cout << "Programmer: Aldo Sanjoto" << endl;
  cout << "Editor(s) used: Code::Blocks " << endl;
  cout << "Compiler(s) used: Code::Blocks" << endl;
  cout << "File: " << __FILE__ << endl;
  cout << "Compiled: " << __DATE__ << " at " << __TIME__ << endl << endl;
}

void table(const course* x)
{
  // Table format
  cout.setf(ios::left, ios::adjustfield);
  cout << setw(10) << "Course" << setw(10) << "Term" << setw(10) << "Units" << setw(10) << "Grade" << endl;
  cout << setw(10) << "------" << setw(10) << "------" << setw(10) << "-----" << setw(10) << "-----" << endl;
}

void printlist(course*& start)
{
   // Print the course
   course* p;
   for (p = start; p; p = p->next)
   cout << setw(10) << p->cs << setw(10) << p->term << setw(10) << p->units << setw(10) << p->grade << endl;
}

int courseCmp(const course* a, const course* b)
{
  // validate the length of the strings
  if((strlen(a->term) != 6) || (strlen(b->term) != 6))
    return myStricmp(a->cs, b->cs);

  // handles ties here
  if(myStricmp(a->term, b->term) == 0)
    return myStricmp(a->cs, b->cs);

  // compare the years
  int yearA = atoi(a->term + 2);
  int yearB = atoi(b->term + 2);
  if(yearA < yearB)
    return -1; // termA comes first
  if(yearA > yearB)
    return 1; // termB comes first

  // compare semesters in case of same year
  if(myStrnicmp(a->term, "SP", 2) == 0)
    return -1;
  if(myStrnicmp(a->term, "SU", 2) == 0)
    return myStrnicmp(b->term, "SP", 2) ? -1 : 1;
  return 1;
}

int myStricmp(const char* x, const char* y)
{
  int f, l;
  do
  {
    if (((f = (unsigned char)(*(x++))) >= 'A') && (f <= 'Z')) f -= 'A' - 'a';
    if (((l = (unsigned char)(*(y++))) >= 'A') && (l <= 'Z')) l -= 'A' - 'a';
  } while (f && (f == l));
  return(f - l);
}

int myStrnicmp(const char* x, const char* y, int z)
{
  int f, l;
  do
  {
    if (((f = (unsigned char)(*(x++))) >= 'A') && (f <= 'Z')) f -= 'A' - 'a';
    if (((l = (unsigned char)(*(y++))) >= 'A') && (l <= 'Z')) l -= 'A' - 'a';
  } while (--z && f && (f == l));
  return (f - l);
}

void restoreList(course*& start, course*& end)
{
  ifstream fin;
  fin.open("courses.dat", ios::binary|ios::in);
  if (!fin)
    return;

  // read the number of objects from the disk file
  int nRecs;
  fin.read((char*)&nRecs, sizeof(int));

  // read the objects from the disk file
  for (int i = 0; i < nRecs; i++)
  {
    course* t = new course;
    fin.read((char*)t, sizeof(course));

    // insert t at the end of the list -- you figure out this part!
    t->next = 0;
    if(end)
      end->next = t;
    else
      start = t;
    end = t;
  }

  // Close file
  fin.close();
}

void saveList(course*& start)
{
  course* prev, *p;
  ofstream fout;
  fout.open("courses.dat", ios::binary|ios::out);
  if(!fout)
    return;

  // Counting number of nodes
  int nRecs = 0;
  for (p = start, prev = 0; p; prev = p, p = p->next)
    nRecs++;

  fout.write((char*)&nRecs, sizeof(int));

  // Write to file
  for (p = start, prev = 0; p; prev = p, p = p->next)
  {
    fout.write((char*)p, sizeof(course));
  }

  // Close file
  fout.close();
}

void sortByUnit(course*& start)
{
  course* newStart = 0;

  while(start)
  {
    course* t = start;
    start = start->next;

    course* p, *prev;

    // Find insertion point in new list
    for (p = newStart, prev = 0; p; prev = p, p = p->next)
    if (p->units > t->units) // if "t" comes before "p"...
      break; // "t" goes between "prev" and "p

    // insert "t" between prev and p
    t->next = p; // "t" inserted before "p"
    if (prev)// "t" inserted after "prev"
      prev->next = t;
    else // "t" added to front of list
      newStart = t;
  }

  start = newStart;
}

void sortByGrade(course*& start)
{
  course* newStart = 0;

  while(start)
  {
    course* t = start;
    start = start->next;

    course* p, *prev;

    // Find insertion point in new list
    for (p = newStart, prev = 0; p; prev = p, p = p->next)
    {
      char y = toupper(t->grade);
      char z = toupper(p->grade);
      if (z > y)
        break;
    }

     // insert "t" between prev and p
    t->next = p; // "t" inserted before "p"
    if (prev)// "t" inserted after "prev"
    prev->next = t;
    else // "t" added to front of list
      newStart = t;
  }

  start = newStart;
}

void sortByDescription(course*& start)
{
  course* newStart = 0;

  while(start)
  {
    course* t = start;
    start = start->next;

    course* p, *prev;

    for (p = newStart, prev = 0; p; prev = p, p = p->next)
    if (myStricmp(t->cs, p->cs) < 0) // if "t" comes before "p"...
      break; // "t" goes between "prev" and "p

    t->next = p; // "t" inserted before "p"
    if (prev)// "t" inserted after "prev"
      prev->next = t;
    else // "t" added to front of list
      newStart = t;
  }

  start = newStart;
}

void sortByTerm(course*& start)
{
  course* newStart = 0;

  while(start)
  {
    course* t = start;
    start = start->next;

    course* p, *prev;

    for (p = newStart, prev = 0; p; prev = p, p = p->next)
    if (courseCmp(t, p) < 0) // if "t" comes before "p"...
      break; // "t" goes between "prev" and "p

    t->next = p; // "t" inserted before "p"
    if (prev)// "t" inserted after "prev"
      prev->next = t;
    else // "t" added to front of list
      newStart = t;
  }

  start = newStart;
}
