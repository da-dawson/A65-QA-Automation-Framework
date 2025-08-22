# Selenium Grid Setup

This directory contains the necessary files to run Selenium Grid in standalone mode for the QA Automation Framework.

## Prerequisites

- Java 8 or higher
- Chrome browser installed on your system

## Setup Instructions

1. **Start Selenium Grid:**
   ```bash
   cd selenium-grid
   ./start-grid.sh
   ```

2. **Verify Grid is Running:**
   - Open your browser and navigate to: `http://localhost:4444`
   - You should see the Selenium Grid console

3. **Stop the Grid:**
   - Press `Ctrl+C` in the terminal where the grid is running

## Grid Configuration

- **Port:** 4444 (default)
- **URL:** http://localhost:4444
- **Browser:** Chrome (configured with selenium-manager)

## Usage in Tests

The `BrowserFactory` class is configured to connect to this local grid. The `BaseTest` class now uses the Browser Factory to create drivers connected to the Grid. Simply run your tests as usual - they will automatically use the grid instead of local drivers.

### Architecture Overview

1. **BrowserFactory**: Creates WebDriver instances connected to Selenium Grid
2. **BaseTest**: Base class that sets up and tears down the Grid-connected driver
3. **Test Classes**: Extend BaseTest to inherit Grid connectivity

## Running Tests

1. Start the Selenium Grid: `./selenium-grid/start-grid.sh`
2. Run your tests: `./gradlew test`
3. Tests will automatically connect to the Grid and run on Chrome

## Troubleshooting

1. **Port already in use:** Make sure no other service is running on port 4444
2. **Chrome not found:** Ensure Chrome browser is installed and accessible
3. **Connection refused:** Verify the grid is running before executing tests

## Files

- `selenium-server-4.25.0.jar` - Selenium Server standalone
- `start-grid.sh` - Script to start the grid
- `README.md` - This documentation
