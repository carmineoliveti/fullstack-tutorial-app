import React, { useState } from "react";
import TutorialService from "../services/TutorialService";

const Tutorial = props => {

    const initialTutorialState = {
        id: null,
        title: "",
        description: "",
        published: false
    };

    const [currentTutorial, setCurrentTutorial] = useState(initialTutorialState);
    const [message, setMessage] = useState("");

    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setCurrentTutorial({ ...currentTutorial, [name]: value });
    };

    const updatePublished = (e) => {
        TutorialService.update(currentTutorial.id, currentTutorial)
            .then(res => {
                console.log(res.data);
                setMessage("The tutorial was update successfully!");
            })
            .catch(e => {
                console.log(e);
            });
    };

    const updateTutorial = (e) => {
        TutorialService.update(currentTutorial.id, currentTutorial)
            .then(response => {
                console.log(response.data);
                setMessage("The tutorial was update successfully!");
            })
            .catch(e => {
                console.log(e);
            });
    };

    const deleteTutorial = (e) => {
        TutorialService.remove(currentTutorial.id)
            .then(res => {
                console.log(res.data);
                props.history.push("/tutorials");
            }).catch(e => {
                console.log(e);
            })
    }
    return (
        <div>
            {currentTutorial ? (
                <div className="edit-form">
                    <h4>Tutorial</h4>
                    <form>
                        <div className="form-group">
                            <label htmlFor="title">Title</label>
                            <input
                                type="text"
                                className="form-control"
                                id="title"
                                name="title"
                                value={currentTutorial.title}
                                onChange={handleInputChange}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="description">Description</label>
                            <input
                                type="text"
                                className="form-control"
                                id="description"
                                name="description"
                                value={currentTutorial.description}
                                onChange={handleInputChange}
                            />
                        </div>
                        <div className="form-group">
                            <label>
                                <strong>Status:</strong>
                            </label>
                            {currentTutorial.published ? "Published" : "Pending"}
                        </div>
                    </form>
                    {currentTutorial.published ? (
                        <button
                            className="badge badge-primary mr-2"
                            onClick={() => updatePublished(false)}
                        >
                            UnPublish
                        </button>
                    ) : (
                        <button
                            className="badge badge-primary mr-2"
                            onClick={() => updatePublished(true)}
                        >
                            Publish
                        </button>
                    )}
                    <button className="badge badge-danger mr-2" onClick={deleteTutorial}>
                        Delete
                    </button>
                    <button
                        type="submit"
                        className="badge badge-success"
                        onClick={updateTutorial}
                    >
                        Update
                    </button>
                    <p>{message}</p>
                </div>
            ) : (
                <div>
                    <br />
                    <p>Please click on a Tutorial...</p>
                </div>
            )}
        </div>
    );
};

export default Tutorial;