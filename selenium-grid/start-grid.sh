#!/bin/bash

echo "Starting Selenium Grid in standalone mode..."
echo "Grid will be available at: http://localhost:4444"
echo "To stop the grid, press Ctrl+C"
echo ""

java -jar selenium-server-4.25.0.jar standalone --port 4444 --selenium-manager true
