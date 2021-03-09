# Automate-WebTable


    // Automate Sorting, Pagination and Filtering of Web tables using Java streams and TestNG.
    
    Requirements:
    Selenium -> Automation framework
    TestNG -> Testing framework
    Maven -> Build Management tool
    Log4j -> Framework logging tool

    // Test URL: https://rahulshettyacademy.com/seleniumPractise/#/offers

    // Test Cases:
    
    // Normal Sort list
    // Steps:
    // 1. Click on column sorting
    // 2. Capture all webelement into list
    // 3. Capture text for all weblelement into new (Original) list
    // 4. Sort original list from step 3 -> sorted list
    // 5. Compare original list vs sorted list

    // Reverse list
    // Steps:
    // 1. Click on column sorting
    // 2. Capture all webelement into list
    // 3. Capture text for all weblelement into new (Original) list
    // 4. Reverse original list from step 3 -> Reversed list
    // 5. Compare original list vs Reversed list
    
    // Get price of selected an items. This is a simple data driven test.
    // Steps:
    // 1. Scan the first colunm
    // 2. Get text of item (Data driven)
    // 3. Get the price of the item

    // Validate search filter. This is a simple data driven test.
    // Steps:
    // 1. Enter search keyword in search bar (Data driven)
    // 2. Validate item in result page
    
