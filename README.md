This is a solid project for your portfolio! Since you are studying at the University of Mannheim, having a professional README in English is a great way to showcase your skills to future employers like SAP or other tech firms in the region.

Here is a comprehensive README text tailored to your specific project requirements:
Academy Awards (Oscar) Data Processor
Project Overview

This project is a Java-based application developed as part of the Programmierpraktikum 1 course at the University of Mannheim. The application processes historical data from the Academy Awards (1927–2025) and actor biographies to provide an interactive filtering and analysis tool.

The core focus of this project is the efficient use of Java Collections, Streams, and Object-Oriented Programming principles.
Key Features

    Data Parsing: Reads and parses CSV datasets for Oscar nominees and actors' life dates (birth/death).

    Custom Object Mapping: Maps raw data to specialized Actor and Nominee objects stored in dynamic collections.

    Advanced Filtering: Extracts specific insights such as:

        Complete award and nomination history for specific actors.

        Lists of actors born in a specific year, sorted by last name.

        Top performers based on nomination and win counts (e.g., Meryl Streep, Katharine Hepburn).

    Interactive GUI: Features a graphical user interface for dynamic data exploration.

    Robust Error Handling: Implements custom exceptions to handle malformed data tokens during the parsing process.

Technical Implementation

    Collections Framework: Utilizes HashMap for efficient O(1) lookups and TreeSet with custom Comparable/Comparator implementations for automatic sorting.

    Java Streams: Leverages the Stream API for modern, declarative data processing and filtering.

    Exception Handling: Custom exception classes and try-catch blocks ensure the application continues processing even if individual data rows are corrupted.

Project Structure

    general: Constants and global parameters.

    model: Core data models (Actor, Nominee) and Enums (Category).

    io: File reading and CSV parsing logic.

    filter: Business logic for data extraction and statistics.

    gui: Swing-based graphical user interface.

    resources: CSV datasets (nominees.csv, actors.csv).

How to Run

    Import the project into Eclipse or IntelliJ IDEA.

    Ensure you are using Java 17 or higher.

    Run the Main class located in the gui package to launch the application.
