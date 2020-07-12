package com.gs.home.datastructures.graph;

import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;

import static com.gs.home.log.Logger.stdout;

public class PrioQueueTest {
    public static void main(String[] args) {
        Queue<VertexWeight> queue = new PriorityQueue<>();

        queue.add(new VertexWeight("A", 2));
        VertexWeight vw = new VertexWeight("C", 100000);
        queue.add(vw);
        queue.add(new VertexWeight("D", 3));
        queue.add(new VertexWeight("E", 100000));

        stdout(queue);
        queue.offer(new VertexWeight("F", 1));
        stdout(queue);

//        VertexWeight qElement = getQueueElement(queue, Flyweight.getVertex("C"));
//        queue.remove(vw);
//        stdout(queue);
        /** WARN: Just updating this way will NOT trigger a resort! */
        vw.weight = 0;
        queue.offer(vw);
        stdout(queue);
    }

    private static VertexWeight getQueueElement(Queue<VertexWeight> queue, Vertex key) {
        Optional<VertexWeight> queueElement = queue.parallelStream().filter(vertexWeight -> vertexWeight.name.equalsIgnoreCase(key.name)).findFirst();
        if (queueElement.isPresent()) {
            VertexWeight element = queueElement.get();
//            stdout("Returning Queue element " + element);
            return element;
        } else {
            stdout("Returning null for " + key.name);
        }

        return null;
    }
}
