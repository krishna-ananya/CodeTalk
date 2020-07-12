## CodeTalk for Eclipse

This repository implements the **CodeTalk** framework for **Eclipse IDE.** CodeTalk is a framework proposed in this CHI'18 paper titled '*[CodeTalk: Improving Programming Environment
Accessibility for Visually Impaired Developers](https://dl.acm.org/doi/10.1145/3173574.3174192)*'. 

The above paper focuses on the accessibility challenges faced by the VI developers in using IDEs. The paper categorizes the accessibility difficulties into four categories Discoverability, Glanceability, Navigability, and Alertability. The paper proposes solutions to some of these challenges and implements these
in CodeTalk, a plugin for Visual Studio.

This code implements a subset of proposed CodeTalk functionality for **Eclipse (any-platform) for Java language**: 

**Summary View**
		- Gives the complete hierarchy of the file in a tree view  which is as below
		- On click in the summary view, the cursor is positioned at that line number.
		- Example:![Snapshot of Summary View](https://github.com/krishna-ananya/CodeTalk/blob/master/snapshots/summary%20view.png)
	

 Context View 
		- Gives the heirarchical context of the cursor position in the java file example: FileName,Class Name at line .. , Function name at line .. , Block (try,while,if,for etc..) at line ..
		- Example:![Snapshot of Context View](https://github.com/krishna-ananya/CodeTalk/blob/master/snapshots/context%20view.png)
	
	
**Function View** 
		- Gives the list of functions with line numbers in the java file that has been opened in eclipse. 
		- The Screen reader user can easily jump to any function using this.
		- Example:![Snapshot of Function View](https://github.com/krishna-ananya/CodeTalk/blob/master/snapshots/function%20view.png)
		
**Move To Context**
		- Moves to the parent block , based on the cursor position.
		- Example:![Snapshot of move to context](https://github.com/krishna-ananya/CodeTalk/blob/master/snapshots/movetoContext.png)

Keyboard shortcuts for codetalk menu
	- Ctrl 6 for Function View
	- Ctrl 7 for Summary View
	- Ctrl 9 for Move to Context
 
**This project welcomes contributions and suggestions. Please raise a pull-request if you want to contribute to this repository**


**Prerequisites / Environment Setup**

Steps to setup the PDE:
- Recent version of java installed
- Download eclipse for plugin development from https://www.eclipse.org/pde/ and install it.

Project Setup Using the wizard:
- Go to File -> New -> Plug-In-Project.
- Enter the name of the project , select the right java envirnoment.
- Click next there are options to select plugin templates in the wizard and Finish (Codetalk uses the plugin template for the menu).
- A Plugin project with the below structure is created
	CodeTalk 
		- JRE System Libraries
		- Plug In Dependancies 
		- src 
			- handlers  
		- Referenced Libraries
		- Plugin.xml 
		- build.properties


How to build project to a plugin jar
- Right Click on the project in eclipse, and select export
- The Export wizard opens up, select the option plug-in development -> Deployeable-plugins-and fragments
- Click on next and specify the path in which the plugin must be created 
- Click on finish and the plugin jar is created.

How to use the plugin with eclipse
- Copy the jar created above to the path  ../eclipse/plugins/
- Restart eclipse and the Plugin is up and running.

