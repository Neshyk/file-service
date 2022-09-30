## This File Service implement basic REST API endpoints for CRUD file operations.

### [More detailed information](https://app.swaggerhub.com/apis/Neshyk/FileService/0.0.1)

### Create: POST /api/files
   - Accepts: multipart/form-data with parts:
   > metadata (basic info about file in JSON: name, size, dateCreated) <br/>
   > file – file bytes <br/>
   - Returns: ID of the created file (String)
   - Returns status code: 201
### Read: GET /api/files/{id}
   - Returns: file contents (binary)
### Read: GET /api/files/{id}/metadata
   - Returns: JSON with metadata
### Delete: DELETE /api/files/{id}
   - Deletes both file and metadata


