CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       first_name VARCHAR(100) UNIQUE NOT NULL,
                       last_name VARCHAR(100) UNIQUE NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       password_hash TEXT,
                       image VARCHAR(100),
                       role VARCHAR(20) NOT NULL
);

CREATE TABLE projects (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          description TEXT,
                          created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE project_members (
                                 id SERIAL PRIMARY KEY,
                                 project_id INTEGER REFERENCES projects(id),
                                 user_id INTEGER REFERENCES users(id),
                                 role VARCHAR(20),
                                 UNIQUE (project_id, user_id)
);

CREATE TABLE task_workflows (
                                id SERIAL PRIMARY KEY,
                                name VARCHAR(100) NOT NULL,  -- Name of the workflow (e.g., "Software Dev Workflow")
                                project_id INTEGER REFERENCES projects(id),
                                created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE workflow_stages (
                                 id SERIAL PRIMARY KEY,
                                 workflow_id INTEGER REFERENCES task_workflows(id),
                                 stage_name VARCHAR(50) NOT NULL,  -- e.g., "Code Review"
                                 position INTEGER NOT NULL  -- Order of the stage in the workflow (e.g., 1 = first step)
);

CREATE TABLE tasks (
                       id SERIAL PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       description TEXT,
                       status VARCHAR(20) CHECK (status IN ('TO_DO', 'IN_PROGRESS', 'DONE')) DEFAULT 'TO_DO',
                       priority VARCHAR(20) CHECK (priority IN ('LOW', 'MEDIUM', 'HIGH')) DEFAULT 'MEDIUM',
                       project_id INTEGER REFERENCES projects(id),
                       assigned_to INTEGER REFERENCES users(id),
                       workflow_stage_id INTEGER REFERENCES workflow_stages(id),
                       created_at TIMESTAMP DEFAULT NOW(),
                       due_date TIMESTAMP NULL
);

CREATE TABLE subtasks (
                          id SERIAL PRIMARY KEY,
                          task_id INTEGER REFERENCES tasks(id),
                          title VARCHAR(255) NOT NULL,
                          status VARCHAR(20) CHECK (status IN ('TO_DO', 'IN_PROGRESS', 'DONE')) DEFAULT 'TO_DO'
);

CREATE TABLE comments (
                          id SERIAL PRIMARY KEY,
                          task_id INTEGER REFERENCES tasks(id),
                          user_id INTEGER REFERENCES users(id),
                          content TEXT NOT NULL,
                          created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE notifications (
                               id SERIAL PRIMARY KEY,
                               user_id INTEGER REFERENCES users(id),
                               message TEXT NOT NULL,
                               is_read BOOLEAN DEFAULT FALSE,
                               created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE activity_logs (
                               id SERIAL PRIMARY KEY,
                               user_id INTEGER REFERENCES users(id),
                               project_id INTEGER REFERENCES projects(id),
                               action TEXT NOT NULL,
                               created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE time_tracking (
                               id SERIAL PRIMARY KEY,
                               task_id INTEGER REFERENCES tasks(id),
                               start_time TIMESTAMP DEFAULT NOW(),
                               end_time TIMESTAMP,    -- Time when the employee stopped tracking
                               status VARCHAR(20) CHECK (status IN ('ONGOING', 'STOPPED')),  -- Track if the time tracking is still ongoing
);
CREATE TABLE project_users (
                               id SERIAL PRIMARY KEY,
                               project_id INT NOT NULL,
                               user_id INT NOT NULL,
                               UNIQUE (project_id, user_id),
                               FOREIGN KEY (project_id) REFERENCES projects(id),
                               FOREIGN KEY (user_id) REFERENCES users(id)
);





