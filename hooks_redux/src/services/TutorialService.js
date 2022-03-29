import http from "./http";

const getAll = () => {
    return http.get("/tutorials");
}

const get = id => {
    return http.get(`/tutorials/${id}`);
}

const create = (tutorial) => {
    return http.post("/tutorials", tutorial);
}

const update = (id, data) => {
    return http.put(`/tutorials/${id}`, data);
}

const remove = id => {
    return http.delete(`/tutorials/${id}`);
}

const removeAll = () => {
    return http.delete(`/tutorials`);
};

const findByTitle = title => {
    return http.get(`/tutorials?title=${title}`);
};

const TutorialService = {
    getAll,
    create,
    get,
    update,
    remove,
    removeAll,
    findByTitle
};

export default TutorialService;